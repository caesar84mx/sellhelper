package com.caesar_84.sellhelper.domain;

import com.caesar_84.sellhelper.domain.basicabstracts.NamedEntity;

public class Client extends NamedEntity {
    private String middleName;

    private String lastName;

    private String contacts;

    private User user;

    public Client() {}

    public Client(String name, String middleName, String lastName, String contacts, User user) {
        this(null, name, middleName, lastName, contacts, user);
    }

    public Client(Integer id, String name, String middleName, String lastName, String contacts, User user) {
        super(id, name);
        this.middleName = middleName;
        this.lastName = lastName;
        this.contacts = contacts;
        this.user = user;
    }

    public String getMiddleName() { return middleName; }

    public void setMiddleName(String middleName) { this.middleName = middleName; }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
