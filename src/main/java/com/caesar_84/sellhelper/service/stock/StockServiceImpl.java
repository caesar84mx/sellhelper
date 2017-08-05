package com.caesar_84.sellhelper.service.stock;

import com.caesar_84.sellhelper.domain.StockItem;
import com.caesar_84.sellhelper.repository.GoodsProviderRepository;
import com.caesar_84.sellhelper.repository.StockItemsRepository;
import com.caesar_84.sellhelper.service.good.GoodService;
import com.caesar_84.sellhelper.service.provider.ProviderService;
import com.caesar_84.sellhelper.util.CheckUtil;
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
        CheckUtil.checkUserIdConsistent(item.getUser(), userId);
        providerService.saveOrUpdate(item.getGood().getProvider(), userId);
        goodsService.saveOrUpdate(item.getGood(), userId);

        return stockItemsRepository.save(item);
    }

    @Override
    public StockItem get(int id, int userId) {
        return stockItemsRepository.getByUserId(id, userId);
    }

    @Override
    public boolean delete(int id, int userId) {
        return stockItemsRepository.deleteByUserId(id, userId) != 0;
    }

    @Override
    public List<StockItem> getAll(int userId) {
        return stockItemsRepository.getAllByUserId(userId);
    }
}
