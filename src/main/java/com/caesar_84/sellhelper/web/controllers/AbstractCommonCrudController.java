package com.caesar_84.sellhelper.web.controllers;

import com.caesar_84.sellhelper.domain.basicabstracts.BaseEntity;
import com.caesar_84.sellhelper.service.CommonCrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class AbstractCommonCrudController<T extends BaseEntity> {
    public static final String BASE_URL = "/rest-api/v1";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private CommonCrudService<T> commonCrudService;

    public AbstractCommonCrudController(CommonCrudService<T> commonCrudService) {
        this.commonCrudService = commonCrudService;
    }

    public T get(int id, int userId) {
        T entity = commonCrudService.get(id, userId);
        logger.info("[Controller] - Getting entity {} for user {}", entity, userId);

        return entity;
    }

    public T post(T entity, int userId) {
        logger.info("[Controller] - Posting entity {} for user {}", entity, userId);

        return commonCrudService.saveOrUpdate(entity, userId);
    }

    public List<T> getAll(int userId) {
        logger.info("[Controller] - Getting all entities for user {}", userId);

        return commonCrudService.getAll(userId);
    }

    public boolean delete(int id, int userId) {
        logger.info("[Controller] - Deleting entity {} for user {}", id, userId);

        return commonCrudService.delete(id, userId);
    }
}