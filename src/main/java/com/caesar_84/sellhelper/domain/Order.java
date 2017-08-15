package com.caesar_84.sellhelper.domain;

import com.caesar_84.sellhelper.domain.auxclasses.Address;
import com.caesar_84.sellhelper.domain.auxclasses.OrderStatus;
import com.caesar_84.sellhelper.domain.basicabstracts.BaseEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @NotNull
    private Client client;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    @NotNull
    private Address shipmentAddress;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyJoinColumn(name = "good_id")
    @Column(name = "quantity")
    private Map<Good, Integer> goods;

    @Column(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Integer userId;

    @Column(name = "created", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    private LocalDateTime created;

    @Column(name = "modified", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDateTime modified;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    public Order() {}

    public Order(Client client, Address shipmentAddress, Map<Good, Integer> goods, Integer userId) {
        this(null, client, shipmentAddress, goods, userId, LocalDateTime.now(), LocalDateTime.now(),
                OrderStatus.PENDING);
    }

    public Order(Client client, Address shipmentAddress, Map<Good, Integer> goods, Integer userId,
                 LocalDateTime created, OrderStatus status) {
        this(null, client, shipmentAddress, goods, userId, created, LocalDateTime.now(), status);
    }

    public Order(Integer id, Client client, Address shipmentAddress, Map<Good, Integer> goods,
                 Integer userId, LocalDateTime created, LocalDateTime modified, OrderStatus status) {
        super(id);
        this.client = client;
        this.shipmentAddress = shipmentAddress;
        this.goods = goods;
        this.userId = userId;
        this.created = created;
        this.modified = modified;
        this.status = status;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + this.getId() +
                ", client=" + client +
                ", shipmentAddress=" + shipmentAddress +
                ", goods=" + goods +
                ", userId=" + userId +
                ", created=" + created +
                ", modified=" + modified +
                ", status=" + status +
                '}';
    }
}