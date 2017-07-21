package com.caesar_84.sellhelper.domain;

import com.caesar_84.sellhelper.domain.basicabstracts.NamedEntity;

public class Good extends NamedEntity {
    private String serialNo;

    private String description;

    private User user;

    private GoodsProvider provider;

    private int price;

    public Good() {}

    public Good(String name, String serialNo, String description, User user,
                GoodsProvider provider, int price) {
        this(null, name, serialNo, description, user, provider, price);
    }

    public Good(Integer id, String name, String serialNo, String description,
                User user, GoodsProvider provider, int price) {
        super(id, name);
        this.serialNo = serialNo;
        this.description = description;
        this.user = user;
        this.provider = provider;
        this.price = price;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GoodsProvider getProvider() {
        return provider;
    }

    public void setProvider(GoodsProvider provider) {
        this.provider = provider;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
