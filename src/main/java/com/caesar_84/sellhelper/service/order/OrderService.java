package com.caesar_84.sellhelper.service.order;

import com.caesar_84.sellhelper.domain.Order;
import com.caesar_84.sellhelper.domain.auxclasses.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    Order save(Order order);

    Order update(Order order);

    boolean delete(int id);

    List<Order> getByStatus(OrderStatus status);

    List<Order> getByStatusForUser(OrderStatus status, int id);

    List<Order> getBetweenForCurrentUser(LocalDate start, LocalDate end);

    List<Order> getBetweenForUser(LocalDate start, LocalDate end, int id);

    List<Order> getByDateForUser(LocalDate date, int id);

    List<Order> getByDateForCurrentUser(LocalDate date);

    List<Order> getByClient(int id);

    List<Order> getAllForUser(int id);

    List<Order> getAllForCurrentUser();
}
