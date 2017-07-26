package com.caesar_84.sellhelper.domain;

import com.caesar_84.sellhelper.domain.basicabstracts.NamedEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "goods")
public class Good extends NamedEntity {
    @Column(name = "serial_no", nullable = false)
    @NotBlank
    private String serialNo;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "provider_id")
    @NotNull
    private GoodsProvider provider;

    @Column(name = "price", nullable = false)
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
