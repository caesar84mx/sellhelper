package com.caesar_84.sellhelper.service.client;

import com.caesar_84.sellhelper.domain.Client;
import com.caesar_84.sellhelper.repository.ClientRepository;
import com.caesar_84.sellhelper.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client saveOrUpdate(Client client, int userId) {
        CheckUtil.checkUserIdConsistent(client.getUser(), userId);

        return clientRepository.save(client);
    }

    @Override
    public Client get(int id, int userId) {
        return clientRepository.get(id, userId);
    }

    @Override
    public boolean delete(int id, int userId) {
        return clientRepository.delete(id, userId) != 0;
    }

    @Override
    public List<Client> getAll(int userId) {
        return clientRepository.getAll(userId);
    }
}