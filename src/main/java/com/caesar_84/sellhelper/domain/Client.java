package com.caesar_84.sellhelper.domain;

import com.caesar_84.sellhelper.domain.basicabstracts.NamedEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "clients")
public class Client extends NamedEntity {
    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    @NotBlank
    private String lastName;

    @Column(name = "contacts", nullable = false)
    @NotBlank
    private String contacts;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
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

    public void setContacts(String contacts) { this.contacts = contacts; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contacts='" + contacts + '\'' +
                ", userId=" + user.getId() +
                '}';
    }
}
