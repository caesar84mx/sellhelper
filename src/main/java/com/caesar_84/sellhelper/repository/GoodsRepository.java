package com.caesar_84.sellhelper.repository;

import com.caesar_84.sellhelper.domain.Good;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Good, Integer> {
}
