package com.caesar_84.sellhelper.service.order;

import com.caesar_84.sellhelper.LoggedUser;
import com.caesar_84.sellhelper.domain.Order;
import com.caesar_84.sellhelper.domain.StockItem;
import com.caesar_84.sellhelper.domain.auxclasses.OrderStatus;
import com.caesar_84.sellhelper.repository.OrderRepository;
import com.caesar_84.sellhelper.repository.StockItemsRepository;
import com.caesar_84.sellhelper.util.CheckUtil;
import com.caesar_84.sellhelper.util.exceptions.EntityNotFoundException;
import com.caesar_84.sellhelper.util.exceptions.NotEnoughItemsException;
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

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockItemsRepository stockItemsRepository;

    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Secured("ROLE_USER")
    @Transactional
    @Override
    public Order save(Order order) {
        logger.info("Saving order {0}", order);
        CheckUtil.checkUserIdConsistent(order.getUser(), LoggedUser.id());

        order.getGoods().forEach((good, quantity) -> {
            StockItem stockItem = stockItemsRepository.getOne(good.getId());
            if (stockItem.getQuantity() - quantity < 0) {
                logger.info("Updating stock");
                throw new NotEnoughItemsException("You don't have enough items \"" +
                        stockItem.getGood().getName() + "\" in your stock!");
            }

            stockItem.setQuantity(stockItem.getQuantity() - quantity);
            stockItemsRepository.save(stockItem);
            logger.info("Stock updated");
        });

        return orderRepository.save(order);
    }

    @Secured("ROLE_USER")
    @Transactional
    @Override
    public Order update(Order order) {
        logger.info("Updating order {0}", order.getId());
        if (order.isNew()) {
            throw new IllegalArgumentException("The order must not be new!");
        }

        CheckUtil.checkUserIdConsistent(order.getUser(), LoggedUser.id());

        Order existentOrder = orderRepository.getOne(order.getId());
        if (existentOrder == null) {
            throw new EntityNotFoundException("Not found order id=" + order.getId());
        }

        order.getGoods().forEach((good, quantity) -> {
            if (existentOrder.getGoods().containsKey(good)) {
                int delta = quantity - existentOrder.getGoods().get(good);
                if (delta != 0) {
                    logger.info("Updating stock");
                    StockItem stockItem = stockItemsRepository.getOne(good.getId());
                    if (stockItem.getQuantity() - delta < 0) {
                        throw new NotEnoughItemsException("You don't have enough items \"" +
                                stockItem.getGood().getName() + "\" in your stock!");
                    } else {
                        stockItem.setQuantity(stockItem.getQuantity() - delta);
                        stockItemsRepository.save(stockItem);
                    }
                    logger.info("Stock updated");
                }
            }
        });

        return orderRepository.save(order);
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
        logger.info("Getting orders gy status {0}", status);
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
        logger.info("Getting orders for period {0} - {1}", start, end);
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
}
