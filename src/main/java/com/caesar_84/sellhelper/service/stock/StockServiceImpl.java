package com.caesar_84.sellhelper.service.stock;

import com.caesar_84.sellhelper.domain.StockItem;
import com.caesar_84.sellhelper.repository.StockItemsRepository;
import com.caesar_84.sellhelper.service.good.GoodService;
import com.caesar_84.sellhelper.service.provider.ProviderService;
import com.caesar_84.sellhelper.util.CheckUtil;
import com.caesar_84.sellhelper.util.exceptions.NotEnoughItemsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockItemsRepository stockItemsRepository;

    @Autowired
    private GoodService goodsService;

    @Autowired
    private ProviderService providerService;

    private Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

    @Override
    public StockItem saveOrUpdate(StockItem item, int userId) {
        if (item.getUserId() == null) {
            item.setUserId(userId);
        }
        CheckUtil.checkUserIdConsistent(item.getUserId(), userId);

        if (item.getQuantity() < 0) {
            throw new NotEnoughItemsException("The quantity value can not be negative!");
        }

        providerService.saveOrUpdate(item.getGood().getProvider(), userId);
        goodsService.saveOrUpdate(item.getGood(), userId);

        return stockItemsRepository.save(item);
    }

    @Override
    public StockItem get(int id, int userId) {
        return stockItemsRepository.get(id, userId);
    }

    @Override
    public boolean delete(int id, int userId) {
        return stockItemsRepository.delete(id, userId) != 0;
    }

    @Override
    public List<StockItem> getAll(int userId) {
        return stockItemsRepository.getAll(userId);
    }
}
