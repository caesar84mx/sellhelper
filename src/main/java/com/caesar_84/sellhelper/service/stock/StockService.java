package com.caesar_84.sellhelper.service.stock;

import com.caesar_84.sellhelper.domain.Good;
import com.caesar_84.sellhelper.domain.StockItem;

import java.util.List;

public interface StockService {
    StockItem save(Good good, int quantity, int userId);

    StockItem update(StockItem item, int userId);

    StockItem get(int id, int userId);

    boolean delete(int id, int userId);

    List<StockItem> getAll(int userId);
}
