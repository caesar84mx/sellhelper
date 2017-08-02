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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
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

    @Override
    public String toString() {
        return "GoodsProvider{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", contacts='" + contacts + '\'' +
                ", user=" + user +
                '}';
    }
}