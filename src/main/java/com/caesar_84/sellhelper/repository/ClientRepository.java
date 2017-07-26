package com.caesar_84.sellhelper.repository;

import com.caesar_84.sellhelper.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
