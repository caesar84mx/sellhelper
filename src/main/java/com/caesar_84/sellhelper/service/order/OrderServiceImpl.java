package com.caesar_84.sellhelper.service.order;

import com.caesar_84.sellhelper.LoggedUser;
import com.caesar_84.sellhelper.domain.Good;
import com.caesar_84.sellhelper.domain.Order;
import com.caesar_84.sellhelper.domain.StockItem;
import com.caesar_84.sellhelper.domain.auxclasses.OrderStatus;
import com.caesar_84.sellhelper.repository.AddressRepository;
import com.caesar_84.sellhelper.repository.ClientRepository;
import com.caesar_84.sellhelper.repository.OrderRepository;
import com.caesar_84.sellhelper.repository.StockItemsRepository;
import com.caesar_84.sellhelper.util.CheckUtil;
import com.caesar_84.sellhelper.util.exceptions.NotEnoughItemsException;
import com.caesar_84.sellhelper.util.exceptions.WrongDateTimeException;
import com.caesar_84.sellhelper.util.exceptions.WrongUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private StockItemsRepository stockItemsRepository;

    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Secured("ROLE_USER")
    @Transactional
    @Override
    public Order saveOrUpdate(Order order) {
        if (order.isNew()) {
            return save(order);
        }

        return update(order);
    }

    @Secured("ROLE_USER")
    @Transactional
    @Override
    public boolean delete(int id) {
        logger.info("Deleting order {0}", id);
        Order order = orderRepository.getOne(id);

        if (!order.getUser().getId().equals(LoggedUser.id())) {
            throw new WrongUserException("You can't delete entity of another user!");
        }

        orderRepository.delete(id);

        return true;
    }

    @Override
    public List<Order> getByStatus(OrderStatus status) {
        logger.info("Getting orders gy status {0} for current user {1}", status, LoggedUser.id());
        return orderRepository.getByStatusAndUserId(status, LoggedUser.id());
    }

    @Secured("ROLE_ADMIN")
    @Override
    public List<Order> getByStatusForUser(OrderStatus status, int id) {
        logger.info("Getting orders by status {0} for user {1}", status, id);
        return orderRepository.getByStatusAndUserId(status, id);
    }

    @Override
    public List<Order> getBetweenForCurrentUser(LocalDate start, LocalDate end) {
        logger.info("Getting orders for period {0} - {1} for current user {2}",
                start, end, LoggedUser.id());

        return orderRepository.getBetweenByUserId(
                LocalDateTime.of(start, LocalTime.of(0, 0)),
                LocalDateTime.of(end, LocalTime.of(0,0)), LoggedUser.id());
    }

    @Secured("ROLE_ADMIN")
    @Override
    public List<Order> getBetweenForUser(LocalDate start, LocalDate end, int id) {
        logger.info("Getting orders for period {0} - {1} for user {2}", start, end, id);
        return orderRepository.getBetweenByUserId(
                LocalDateTime.of(start, LocalTime.of(0, 0)),
                LocalDateTime.of(end, LocalTime.of(0,0)), id);
    }


    @Secured("ROLE_ADMIN")
    @Override
    public List<Order> getByDateForUser(LocalDate date, int id) {
        logger.info("Getting orders by date {0} for user {1}", date, id);
        LocalDateTime nextDay = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 0, 0);
        return orderRepository.getBetweenByUserId(
                LocalDateTime.of(date, LocalTime.of(0, 0)),
                nextDay, id);
    }

    @Override
    public List<Order> getByDateForCurrentUser(LocalDate date) {
        logger.info("Getting orders by date {0} for logged user {1}", date, LoggedUser.id());
        LocalDateTime nextDay = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 0, 0);
        return orderRepository.getBetweenByUserId(
                LocalDateTime.of(date, LocalTime.of(0, 0)),
                nextDay, LoggedUser.id());
    }

    @Override
    public List<Order> getByClient(int id) {
        logger.info("Getting orders by client {0} for logged user {1}", id, LoggedUser.id());
        return orderRepository.getByClientAndUserId(id, LoggedUser.id());
    }

    @Secured("ROLE_ADMIN")
    @Override
    public List<Order> getAllForUser(int id) {
        logger.info("Getting orders for user {0}", id);
        return orderRepository.getAllByUserId(id);
    }

    @Override
    public List<Order> getAllForCurrentUser() {
        logger.info("Getting orders for user {0}", LoggedUser.id());
        return orderRepository.getAllByUserId(LoggedUser.id());
    }

    //Auxiliary methods

    private Order save(Order order) {
        logger.debug("Saving order {0}", order);

        //Let's check first if this order belongs to a logged user, ...
        CheckUtil.checkUserIdConsistent(order.getUser(), LoggedUser.id());
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
        updateStock(order.getGoods());

        //Save the order's entity parts and reassign them
        logger.debug("Saving orders client and shipment address");
        order.setClient(clientRepository.save(order.getClient()));
        order.setShipmentAddress(addressRepository.save(order.getShipmentAddress()));

        //And save and return the order
        return orderRepository.save(order);
    }

    private Order update(Order order) {
        logger.debug("Updating order {0}", order.getId());

        //Check that the order's user coincides with the logged one
        CheckUtil.checkUserIdConsistent(order.getUser(), LoggedUser.id());

        //Get the order we are updating and check if it exists
        Order existentOrder = orderRepository.findOne(order.getId());
        CheckUtil.checkNotNull(order);

        //Check if order has not changed
        if (order.equals(existentOrder)) {
            return order;
        }

        //Check if only status has changed
        if (CheckUtil.onlyStatusChanged(order, existentOrder)) {
            order.setModified(LocalDateTime.now());
            return orderRepository.save(order);
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
        compareAndUpdateStock(order.getGoods(), existentOrder.getGoods());

        order.setModified(LocalDateTime.now());

        return orderRepository.save(order);
    }

    private void compareAndUpdateStock(Map<Good, Integer> newItems, Map<Good, Integer> oldItems) {
        newItems.forEach((good, quantity) -> {
            //See if there is such item in the store
            StockItem stockItem = stockItemsRepository.findOne(good.getId());
            CheckUtil.checkNotNull(stockItem);

            //If the item is already contained in the old order
            if (oldItems.containsKey(good)) {
                //Calculate delta between new and old items quantity
                int delta = quantity - oldItems.get(good);

                //If the quantity was changed
                if (delta != 0) {
                    //If there are no enough items in the store, throw an exception
                    if (stockItem.getQuantity() - delta < 0) {
                        throw new NotEnoughItemsException("You have no enough items " +
                                "in the store!");
                    } else {
                        //Else subtract the delta from the stock item quantity
                        //and assign it a new quantity value
                        stockItem.setQuantity(stockItem.getQuantity() - delta);
                    }
                }
                //If the item is not contained in the old order
            } else {
                //If there are no enough items in the store, throw an exception
                if (stockItem.getQuantity() - quantity < 0) {
                    throw new NotEnoughItemsException("You have no enough items " +
                            "in the store!");
                } else {
                    //Else subtract the retrieving quantity from the stock
                    //and assign a new quantity value to the stock item
                    stockItem.setQuantity(stockItem.getQuantity() - quantity);
                }
            }
            //And save the item in the store
            stockItemsRepository.save(stockItem);
        });
    }

    private void updateStock(Map<Good, Integer> orderItems) {
        logger.debug("Updating stock");

        orderItems.forEach((good, quantity) -> {
            //Let's check if the item exists in the stock
            StockItem stockItem = stockItemsRepository.getOne(good.getId());
            CheckUtil.checkNotNull(stockItem);

            //If there are no enough items in the user's stock, throw an exception
            if (stockItem.getQuantity() - quantity < 0) {
                logger.error("Not enough items in the stock!");
                throw new NotEnoughItemsException("You don't have enough items \"" +
                        stockItem.getGood().getName() + "\" in your stock!");
            }

            logger.info("Updating stock");

            //And then, update the stock
            stockItem.setQuantity(stockItem.getQuantity() - quantity);
            stockItemsRepository.save(stockItem);

            logger.info("Stock updated");
        });
    }
}