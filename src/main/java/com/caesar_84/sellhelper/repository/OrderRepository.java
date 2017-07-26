package com.caesar_84.sellhelper.repository;

import com.caesar_84.sellhelper.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order, Integer> {
}
