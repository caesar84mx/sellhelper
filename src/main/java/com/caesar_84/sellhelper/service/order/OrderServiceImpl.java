package com.caesar_84.sellhelper.service.order;

import com.caesar_84.sellhelper.domain.Good;
import com.caesar_84.sellhelper.domain.Order;
import com.caesar_84.sellhelper.domain.StockItem;
import com.caesar_84.sellhelper.domain.auxclasses.OrderStatus;
import com.caesar_84.sellhelper.repository.OrderRepository;
import com.caesar_84.sellhelper.service.address.AddressService;
import com.caesar_84.sellhelper.service.client.ClientService;
import com.caesar_84.sellhelper.service.stock.StockService;
import com.caesar_84.sellhelper.util.CheckUtil;
import com.caesar_84.sellhelper.util.exceptions.WrongDateTimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private StockService stockItemsService;

    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public Order saveOrUpdate(Order order, int userId) {
        if (order.isNew()) {
            return save(order, userId);
        }

        return update(order, userId);
    }

    @Override
    public boolean delete(int id, int userId) {
        logger.info("Deleting order {} of user {}", id, userId);

        return orderRepository.delete(id, userId) != 0;
    }

    @Override
    public Order get(int id, int userId) {
        logger.info("User {} is trying to get an order {}", userId, id);
        return orderRepository.get(id, userId);
    }

    @Override
    public boolean changeStatus(int id, int userId, OrderStatus status) {
        logger.info("User {} is changing status on order {} to {}", userId, id, status);
        return orderRepository.changeStatus(id, userId, status) != 0;
    }

    @Override
    public List<Order> getByStatus(OrderStatus status, int userId) {
        logger.info("Getting orders by status {} for user {}", status, userId);
        return orderRepository.getByStatus(status, userId);
    }

    @Override
    public List<Order> getBetween(LocalDateTime start, LocalDateTime end, int userId) {
        logger.info("Getting orders for period {} - {} for user {}", start, end, userId);
        return orderRepository.getBetween(start, end, userId);
    }

    @Override
    public List<Order> getByClient(int clientId, int userId) {
        logger.info("Getting orders by client {} for logged user {}", clientId, userId);
        return orderRepository.getByClient(clientId, userId);
    }

    @Override
    public List<Order> getAll(int userId) {
        logger.info("Getting orders for user {}", userId);
        return orderRepository.getAll(userId);
    }

    //Auxiliary methods

    private Order save(Order order, int userId) {
        logger.debug("Saving order {} for user {}", order, userId);

        //Let's check first if this order belongs to a logged user, ...
        if (order.getUserId() == null) {
            order.setUserId(userId);
        }

        CheckUtil.checkUserIdConsistent(order.getUserId(), userId);
        //... has a correct PENDING status...
        CheckUtil.checkOrderStatus(order, OrderStatus.PENDING);

        //... and the creation and modification date and time coincide
        if (order.getCreated() != null) {
            if (order.getModified() != null) {
                if (!order.getCreated().equals(order.getModified())) {
                    throw new WrongDateTimeException("The creation and modifying date and time " +
                            "don't coincide");
                }
            }
        }

        //Update the stock
        updateStock(order.getGoods(), userId);

        //Save the order's entity parts and reassign them
        logger.debug("Saving orders client and shipment address");
        order.setClient(clientService.saveOrUpdate(order.getClient(), userId));
        order.setShipmentAddress(addressService.saveOrUpdate(order.getShipmentAddress(), userId));

        //And save and return the order
        return orderRepository.save(order);
    }

    private Order update(Order order, int userId) {
        logger.debug("Updating order {} for user {}", order.getId(), userId);

        //Check that the order's user coincides with the logged one
        if (order.getUserId() == null) {
            order.setUserId(userId);
        }
        CheckUtil.checkUserIdConsistent(order.getUserId(), userId);

        //Get the order we are updating and check if it exists
        Order existentOrder = orderRepository.findOne(order.getId());
        CheckUtil.checkNotNull(order);

        //Check if order has not changed
        if (order.equals(existentOrder)) {
            return order;
        }

        //Check that the both orders are at PENDING status
        CheckUtil.checkOrderStatus(existentOrder, OrderStatus.PENDING);
        CheckUtil.checkOrderStatus(order, OrderStatus.PENDING);

        //Check if the creation date and time did not change
        if (order.getCreated().equals(existentOrder.getCreated())) {
            throw new WrongDateTimeException("The creation date and time do not coincide!");
        }

        //Check if the creation date and time are not later than the modification ones
        if (order.getCreated().isAfter(order.getModified())) {
            throw new WrongDateTimeException("The creation date and time may not be later " +
                    "than the modification ones!");
        }

        //Compare and update the stock
        compareAndUpdateStock(order.getGoods(), existentOrder.getGoods(), userId);

        //Set new modification date and time
        order.setModified(LocalDateTime.now());

        return orderRepository.save(order);
    }

    private void compareAndUpdateStock(Map<Good, Integer> newItems, Map<Good, Integer> oldItems, int userId) {
        newItems.forEach((good, quantity) -> {
            //See if there is such item in the store
            StockItem stockItem = stockItemsService.get(good.getId(), userId);
            CheckUtil.checkNotNull(stockItem);

            //If the item is already contained in the old order
            if (oldItems.containsKey(good)) {
                //Calculate delta between new and old items quantity
                int delta = quantity - oldItems.get(good);

                //If the quantity was changed
                if (delta != 0) {
                    //If there are no enough items in the store, it will throw an exception
                    //This is implemented in StockServiceImpl
                    //So, we subtract the delta from the stock item quantity
                    //and assign it a new quantity value
                    stockItem.setQuantity(stockItem.getQuantity() - delta);
                }
                //If the item is not contained in the old order
            } else {
                //If there are no enough items in the store, it will throw an exception
                //This is implemented in StockServiceImpl
                //So, we subtract the retrieving quantity from the stock
                //and assign a new quantity value to the stock item
                stockItem.setQuantity(stockItem.getQuantity() - quantity);
            }
            //And save the item in the store
            stockItemsService.saveOrUpdate(stockItem, userId);
        });
    }

    private void updateStock(Map<Good, Integer> orderItems, int userId) {
        logger.debug("Updating stock");

        orderItems.forEach((good, quantity) -> {
            //Let's check if the item exists in the stock
            StockItem stockItem = stockItemsService.get(good.getId(), userId);
            CheckUtil.checkNotNull(stockItem);

            //And then, update the stock
            //If the resulting quantity is below zero, it will throw an exception
            //As implemented in StockServiceImpl
            stockItem.setQuantity(stockItem.getQuantity() - quantity);
            stockItemsService.saveOrUpdate(stockItem, userId);

            logger.debug("Stock updated");
        });
    }
}