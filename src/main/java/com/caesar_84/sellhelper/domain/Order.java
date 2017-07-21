package com.caesar_84.sellhelper.domain;

import com.caesar_84.sellhelper.domain.auxclasses.Address;
import com.caesar_84.sellhelper.domain.basicabstracts.BaseEntity;

import java.util.Map;

public class Order extends BaseEntity {
    private Client client;

    private Address shipmentAddress;

    private Map<Good, Integer> goods;

    private User user;

    public Order() {}

    public Order(Client client, Address shipmentAddress, Map<Good, Integer> goods, User user) {
        this(null, client, shipmentAddress, goods, user);
    }

    public Order(Integer id, Client client, Address shipmentAddress,
                 Map<Good, Integer> goods, User user) {
        super(id);
        this.client = client;
        this.shipmentAddress = shipmentAddress;
        this.goods = goods;
        this.user = user;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Address getShipmentAddress() {
        return shipmentAddress;
    }

    public void setShipmentAddress(Address shipmentAddress) {
        this.shipmentAddress = shipmentAddress;
    }

    public Map<Good, Integer> getGoods() {
        return goods;
    }

    public void setGoods(Map<Good, Integer> goods) {
        this.goods = goods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
