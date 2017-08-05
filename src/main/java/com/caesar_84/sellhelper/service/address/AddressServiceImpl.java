package com.caesar_84.sellhelper.service.address;

import com.caesar_84.sellhelper.domain.auxclasses.Address;
import com.caesar_84.sellhelper.repository.AddressRepository;
import com.caesar_84.sellhelper.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address saveOrUpdate(Address address, int userId) {
        CheckUtil.checkUserIdConsistent(address.getUser(), userId);

        return addressRepository.save(address);
    }

    @Override
    public Address get(int id, int userId) {
        Address address = addressRepository.findOne(id);
        CheckUtil.checkUserIdConsistent(address.getUser(), userId);

        return address;
    }

    @Override
    public boolean delete(int id, int userId) {
        return addressRepository.deleteByUserId(id, userId) != 0;
    }

    @Override
    public List<Address> getAll(int userId) {
        return addressRepository.getAllByUserId(userId);
    }
}