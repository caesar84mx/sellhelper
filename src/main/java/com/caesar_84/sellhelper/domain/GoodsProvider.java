package com.caesar_84.sellhelper.domain;

import com.caesar_84.sellhelper.domain.basicabstracts.NamedEntity;

public class GoodsProvider extends NamedEntity {
    private String contacts;

    private User user;

    public GoodsProvider() {}

    public GoodsProvider(String name, String contacts, User user) {
        this(null, name, contacts, user);
    }

    public GoodsProvider(Integer id, String name, String contacts, User user) {
        super(id, name);
        this.contacts = contacts;
        this.user = user;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}