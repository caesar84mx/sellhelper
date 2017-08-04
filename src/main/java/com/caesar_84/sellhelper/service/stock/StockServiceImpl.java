package com.caesar_84.sellhelper.service.stock;

import com.caesar_84.sellhelper.domain.Good;
import com.caesar_84.sellhelper.domain.StockItem;
import com.caesar_84.sellhelper.repository.GoodsRepository;
import com.caesar_84.sellhelper.repository.StockItemsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockItemsRepository stockItemsRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    private Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

    @Secured("ROLE_USER")
    @Transactional
    @Override
    public StockItem save(Good good, int quantity, int userId) {
        return null;
    }

    @Secured("ROLE_USER")
    @Transactional
    @Override
    public StockItem update(StockItem item, int userId) {
        return null;
    }

    @Transactional
    @Override
    public StockItem get(int id, int userId) {
        return null;
    }

    @Transactional
    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Transactional
    @Override
    public List<StockItem> getAll(int userId) {
        return null;
    }
}
