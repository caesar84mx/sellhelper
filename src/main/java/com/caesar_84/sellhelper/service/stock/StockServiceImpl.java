package com.caesar_84.sellhelper.service.stock;

import com.caesar_84.sellhelper.domain.Good;
import com.caesar_84.sellhelper.domain.StockItem;
import com.caesar_84.sellhelper.repository.GoodsRepository;
import com.caesar_84.sellhelper.repository.StockItemsRepository;
import com.caesar_84.sellhelper.util.CheckUtil;
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
        CheckUtil.checkUserIdConsistent(good.getUser(), userId);

        Good savedGood = goodsRepository.save(good);

        return stockItemsRepository.save(new StockItem(savedGood, quantity, savedGood.getUser()));
    }

    @Secured("ROLE_USER")
    @Transactional
    @Override
    public StockItem update(StockItem item, int userId) {
        CheckUtil.checkUserIdConsistent(item.getUser(), userId);
        goodsRepository.save(item.getGood());

        return stockItemsRepository.save(item);
    }

    @Override
    public StockItem get(int id, int userId) {
        return stockItemsRepository.getByUserId(id, userId);
    }

    @Secured("ROLE_USER")
    @Transactional
    @Override
    public boolean delete(int id, int userId) {
        return stockItemsRepository.deleteByUserId(id, userId) != 0;
    }

    @Override
    public List<StockItem> getAll(int userId) {
        return stockItemsRepository.getAllByUserId(userId);
    }
}
