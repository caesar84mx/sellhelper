package com.caesar_84.sellhelper.repository;

import com.caesar_84.sellhelper.domain.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Good, Integer> {
    @Transactional(readOnly = true)
    @Query("SELECT g FROM Good g WHERE g.id=:id AND g.user.id=:user_id")
    Good get(@Param("id") int id, @Param("user_id") int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Good g WHERE g.id=:id AND g.user.id=:user_id")
    int delete(@Param("id") int id, @Param("user_id") int userId);

    @Transactional(readOnly = true)
    @Query("SELECT g FROM Good g WHERE g.user.id=:user_id")
    List<Good> getAll(@Param("user_id") int userId);
}