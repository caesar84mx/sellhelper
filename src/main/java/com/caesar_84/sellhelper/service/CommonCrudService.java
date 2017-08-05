package com.caesar_84.sellhelper.service;

import com.caesar_84.sellhelper.domain.basicabstracts.BaseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommonCrudService <T extends BaseEntity> {
    @Transactional
    @Secured("ROLE_USER")
    T saveOrUpdate(T entity, int userId);

    T get(int id, int userId);

    @Transactional
    @Secured("ROLE_USER")
    boolean delete(int id, int userId);

    List<T> getAll(int userId);
}