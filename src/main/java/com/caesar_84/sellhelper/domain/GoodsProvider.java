package com.caesar_84.sellhelper.domain;

import com.caesar_84.sellhelper.domain.basicabstracts.NamedEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "providers")
public class GoodsProvider extends NamedEntity {
    @Column(name = "contacts", nullable = false)
    @NotBlank
    private String contacts;

    @Column(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Integer userId;

    public GoodsProvider() {}

    public GoodsProvider(String name, String contacts, Integer userId) {
        this(null, name, contacts, userId);
    }

    public GoodsProvider(Integer id, String name, String contacts, Integer userId) {
        super(id, name);
        this.contacts = contacts;
        this.userId = userId;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "GoodsProvider{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", contacts='" + contacts + '\'' +
                ", userId=" + userId +
                '}';
    }
}