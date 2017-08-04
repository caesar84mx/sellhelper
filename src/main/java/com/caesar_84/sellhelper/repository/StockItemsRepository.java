package com.caesar_84.sellhelper.repository;

import com.caesar_84.sellhelper.domain.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StockItemsRepository extends JpaRepository<StockItem, Integer> {
    @Query("SELECT i FROM StockItem i WHERE i.id=:item_id AND i.user.id=:user_id")
    StockItem getByUserId(@Param("item_id") int id, @Param("user_id") int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM StockItem i WHERE i.id=:item_id AND i.user.id=:user_id")
    int deleteByUserId(@Param("item_id") int id, @Param("user_id") int userId);

    @Query("SELECT i FROM StockItem i WHERE i.user.id=:user_id")
    List<StockItem> getAllByUserId(@Param("user_id") int userId);
}
