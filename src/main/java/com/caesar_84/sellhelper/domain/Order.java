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

    //@OneToMany
    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "good_id")
    @Column(name = "quantity")
    @CollectionTable(name = "order_items", joinColumns = {
            @JoinColumn(name = "order_id"),
            /*@JoinColumn(name = "good_id")*/
    })
    private Map<Good, Integer> goods;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

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

    public Order(Client client, Address shipmentAddress, Map<Good, Integer> goods, User user) {
        this(null, client, shipmentAddress, goods, user, LocalDateTime.now(), LocalDateTime.now(),
                OrderStatus.PENDING);
    }

    public Order(Client client, Address shipmentAddress, Map<Good, Integer> goods, User user,
                 LocalDateTime created, OrderStatus status) {
        this(null, client, shipmentAddress, goods, user, created, LocalDateTime.now(), status);
    }

    public Order(Integer id, Client client, Address shipmentAddress, Map<Good, Integer> goods,
                 User user, LocalDateTime created, LocalDateTime modified, OrderStatus status) {
        super(id);
        this.client = client;
        this.shipmentAddress = shipmentAddress;
        this.goods = goods;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}