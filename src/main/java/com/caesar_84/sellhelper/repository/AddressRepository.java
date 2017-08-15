package com.caesar_84.sellhelper.repository;

import com.caesar_84.sellhelper.domain.auxclasses.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Transactional(readOnly = true)
    @Query("SELECT a FROM Address a WHERE a.id=:id AND a.userId=:user_id")
    Address get(@Param("id") int id, @Param("user_id") int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Address a WHERE a.id=:id AND a.userId=:user_id")
    int delete(@Param("id") int id, @Param("user_id") int userId);

    @Transactional(readOnly = true)
    @Query("SELECT a FROM Address a WHERE a.userId=:user_id ORDER BY a.country, a.location ASC")
    List<Address> getAll(@Param("user_id") int userId);
}