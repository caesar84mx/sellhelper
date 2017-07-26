package com.caesar_84.sellhelper.repository;

import com.caesar_84.sellhelper.domain.auxclasses.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
