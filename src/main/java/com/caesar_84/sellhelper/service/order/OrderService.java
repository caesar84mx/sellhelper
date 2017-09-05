package com.caesar_84.sellhelper.service.order;

import com.caesar_84.sellhelper.domain.Order;
import com.caesar_84.sellhelper.domain.auxclasses.OrderStatus;
import com.caesar_84.sellhelper.service.CommonCrudService;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService extends CommonCrudService<Order> {
    boolean changeStatus(int id, int userId, OrderStatus status);

    List<Order> getByClient(int clientId, int userId);

    List<Order> getByStatus(OrderStatus status, int userId);

    List<Order> getBetween(LocalDateTime start, LocalDateTime end, int userId);
}