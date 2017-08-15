package com.caesar_84.sellhelper.repository;

import com.caesar_84.sellhelper.domain.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface StockItemsRepository extends JpaRepository<StockItem, Integer> {
    @Transactional(readOnly = true)
    @Query("SELECT i FROM StockItem i WHERE i.id=:item_id AND i.userId=:user_id")
    StockItem get(@Param("item_id") int id, @Param("user_id") int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM StockItem i WHERE i.id=:item_id AND i.userId=:user_id")
    int delete(@Param("item_id") int id, @Param("user_id") int userId);

    @Transactional(readOnly = true)
    @Query("SELECT i FROM StockItem i WHERE i.userId=:user_id")
    List<StockItem> getAll(@Param("user_id") int userId);
}
