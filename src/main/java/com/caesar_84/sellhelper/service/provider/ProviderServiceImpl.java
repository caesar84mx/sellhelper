package com.caesar_84.sellhelper.service.provider;

import com.caesar_84.sellhelper.domain.GoodsProvider;
import com.caesar_84.sellhelper.repository.GoodsProviderRepository;
import com.caesar_84.sellhelper.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    GoodsProviderRepository providerRepository;

    @Override
    public GoodsProvider saveOrUpdate(GoodsProvider provider, int userId) {
        CheckUtil.checkUserIdConsistent(provider.getUser(), userId);

        return providerRepository.save(provider);
    }

    @Override
    public GoodsProvider get(int id, int userId) {
        return providerRepository.get(id, userId);
    }

    @Override
    public boolean delete(int id, int userId) {
        return providerRepository.delete(id, userId) != 0;
    }

    @Override
    public List<GoodsProvider> getAll(int userId) {
        return providerRepository.getAll(userId);
    }
}
