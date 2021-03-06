package com.caesar_84.sellhelper.repository;

import com.caesar_84.sellhelper.domain.Order;
import com.caesar_84.sellhelper.domain.auxclasses.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestResource(exported = false)
public interface OrderRepository  extends JpaRepository<Order, Integer> {
    @Transactional(readOnly = true)
    @Query("SELECT o FROM Order o WHERE o.user.id=:user_id AND o.id=:order_id")
    Order get(@Param("order_id") int orderId, @Param("user_id") int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Order o WHERE o.id=:order_id AND o.user.id=:user_id")
    int delete(@Param("order_id") int id, @Param("user_id") int userId);

    @Transactional
    @Modifying
    @Query("UPDATE Order o SET o.status=:status WHERE o.id=:id AND o.user.id=:user_id")
    int changeStatus(@Param("id") int id,
                     @Param("user_id") int userId,
                     @Param("status") OrderStatus status);

    @Transactional(readOnly = true)
    @Query("SELECT o FROM Order o WHERE o.status=:status AND o.user.id=:user_id ORDER BY o.modified DESC")
    List<Order> getByStatus(@Param("status") OrderStatus status,
                            @Param("user_id") int userId);

    @Transactional(readOnly = true)
    @Query("SELECT o FROM Order o WHERE o.user.id=:user_id AND o.created BETWEEN :start AND :end ORDER BY o.modified DESC")
    List<Order> getBetween(@Param("start") LocalDateTime start,
                           @Param("end") LocalDateTime end,
                           @Param("user_id") int id);

    @Transactional(readOnly = true)
    @Query("SELECT o FROM Order o WHERE o.user.id=:user_id AND o.client.id=:client_id ORDER BY o.modified DESC")
    List<Order> getByClient(@Param("client_id") int clientId, @Param("user_id") int userId);

    @Transactional(readOnly = true)
    @Query("SELECT o FROM Order o WHERE o.user.id=:user_id ORDER BY o.modified DESC")
    List<Order> getAll(@Param("user_id") int userId);
}