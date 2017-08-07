package com.caesar_84.sellhelper.repository;

import com.caesar_84.sellhelper.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Transactional(readOnly = true)
    @Query("SELECT c FROM Client c where c.id=:id AND c.user.id=:user_id")
    Client get(@Param("id") int id, @Param("user_id") int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Client c WHERE c.id=:id AND c.user.id=:user_id")
    int delete(@Param("id") int id, @Param("user_id") int userId);

    @Transactional(readOnly = true)
    @Query("SELECT c FROM Client c WHERE c.user.id=:user_id ORDER BY c.name, c.middleName, c.lastName")
    List<Client> getAll(@Param("user_id") int userId);
}
