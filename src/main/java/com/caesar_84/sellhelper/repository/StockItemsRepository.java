package com.caesar_84.sellhelper.repository;

import com.caesar_84.sellhelper.domain.StockItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockItemsRepository extends JpaRepository<StockItems, Integer> {
}
