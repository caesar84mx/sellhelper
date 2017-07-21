package com.caesar_84.sellhelper.domain;

import com.caesar_84.sellhelper.domain.auxclasses.Address;
import com.caesar_84.sellhelper.domain.basicabstracts.NamedEntity;

import java.util.List;

public class Client extends NamedEntity {
    private String lastName;

    private String contacts;

    private List<Address> addressList;

    private User user;

    public Client() {}

    public Client(String name, String lastName, String contacts, List<Address> addressList, User user) {
        this(null, name, lastName, contacts, addressList, user);
    }

    public Client(Integer id, String name, String lastName, String contacts,
                  List<Address> addressList, User user) {
        super(id, name);
        this.lastName = lastName;
        this.contacts = contacts;
        this.addressList = addressList;
        this.user = user;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
