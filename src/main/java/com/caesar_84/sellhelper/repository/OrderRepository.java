package com.caesar_84.sellhelper.repository;

import com.caesar_84.sellhelper.domain.Order;
import com.caesar_84.sellhelper.domain.auxclasses.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository  extends JpaRepository<Order, Integer> {
    @Transactional(readOnly = true)
    @Query("SELECT o FROM Order o WHERE o.user.id=:user_id AND o.id=:order_id")
    Order getForUserById(@Param("order_id") int orderId, @Param("user_id") int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Order o WHERE o.id=:order_id AND o.user.id=:user_id")
    int deleteByUserId(@Param("order_id") int id, @Param("user_id") int userId);

    @Transactional(readOnly = true)
    @Query("SELECT o FROM Order o WHERE o.status=:status AND o.user.id=:user_id")
    List<Order> getByStatusAndUserId(@Param("status") OrderStatus status,
                                     @Param("user_id") int userId);

    @Transactional(readOnly = true)
    @Query("SELECT o FROM Order o WHERE o.id=:id AND o.created BETWEEN :start AND :end")
    List<Order> getBetweenByUserId(@Param("start") LocalDateTime start,
                                   @Param("end") LocalDateTime end,
                                   @Param("id") int id);

    @Transactional(readOnly = true)
    @Query("SELECT o FROM Order o WHERE o.user.id=:user_id AND o.client.id=:client_id")
    List<Order> getByClientAndUserId(@Param("client_id") int clientId, @Param("user_id") int userId);

    @Transactional(readOnly = true)
    @Query("SELECT o FROM Order o WHERE o.user.id=:user_id")
    List<Order> getAllByUserId(@Param("user_id") int userId);
}