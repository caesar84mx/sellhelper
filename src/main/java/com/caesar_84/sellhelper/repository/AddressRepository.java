package com.caesar_84.sellhelper.repository;

import com.caesar_84.sellhelper.domain.auxclasses.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Address a WHERE a.id=:id AND a.user.id=:user_id")
    int deleteByUserId(@Param("id") int id, @Param("user_id") int userId);

    @Query("SELECT a FROM Address a WHERE a.user.id=:user_id ORDER BY a.country, a.location ASC")
    List<Address> getAllByUserId(@Param("user_id") int userId);
}