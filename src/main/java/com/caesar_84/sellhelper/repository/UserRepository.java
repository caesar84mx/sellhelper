package com.caesar_84.sellhelper.repository;

import com.caesar_84.sellhelper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<User, Integer> {
    @Transactional(readOnly = true)
    @Query("SELECT u FROM User u WHERE u.email=:email")
    User findByEmail(@Param("email") String email);

    @Secured("ROLE_ADMIN")
    @Transactional
    @Query("UPDATE User u SET u.enabled=:enabled WHERE u.id=:id")
    int setEnabled(@Param("id") int id, @Param("enabled") boolean enabled);
}