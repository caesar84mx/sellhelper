package com.caesar_84.sellhelper.service.good;

import com.caesar_84.sellhelper.domain.Good;
import com.caesar_84.sellhelper.repository.GoodsRepository;
import com.caesar_84.sellhelper.service.provider.ProviderService;
import com.caesar_84.sellhelper.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    private ProviderService providerService;

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public Good saveOrUpdate(Good good, int userId) {
        if (good.getUserId() == null) {
            good.setUserId(userId);
        }

        CheckUtil.checkUserIdConsistent(good.getUserId(), userId);

        if (good.getPrice() < 0) {
            throw new IllegalArgumentException("The price can not be negative! (but the quality does...)");
        }

        providerService.saveOrUpdate(good.getProvider(), userId);

        return goodsRepository.save(good);
    }

    @Override
    public Good get(int id, int userId) {

        return goodsRepository.get(id, userId);
    }

    @Override
    public boolean delete(int id, int userId) {
        return goodsRepository.delete(id, userId) != 0;
    }

    @Override
    public List<Good> getAll(int userId) {
        return goodsRepository.getAll(userId);
    }
}
