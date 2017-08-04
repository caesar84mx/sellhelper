package com.caesar_84.sellhelper.service.order;

import com.caesar_84.sellhelper.domain.Order;
import com.caesar_84.sellhelper.domain.auxclasses.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    Order saveOrUpdate(Order order, int userId);

    boolean delete(int id, int userId);

    Order get(int id, int userId);

    List<Order> getByClient(int clientId, int userId);

    List<Order> getByStatus(OrderStatus status, int userId);

    List<Order> getBetween(LocalDateTime start, LocalDateTime end, int userId);

    List<Order> getAll(int userId);
}
