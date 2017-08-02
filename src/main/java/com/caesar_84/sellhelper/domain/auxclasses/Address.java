package com.caesar_84.sellhelper.domain.auxclasses;

import com.caesar_84.sellhelper.domain.User;
import com.caesar_84.sellhelper.domain.basicabstracts.BaseEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "addresses")
public class Address extends BaseEntity {
    @Column(name = "country", nullable = false)
    @NotBlank
    private String country;

    @Column(name = "region", nullable = false)
    @NotBlank
    private String region;

    @Column(name = "location", nullable = false)
    @NotBlank
    private String location;

    @Column(name = "details", nullable = false)
    @NotBlank
    private String details;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    public Address() {}

    public Address(String country, String region, String location, String details, User user) {
        this(null, country, region, location, details, user);
    }

    public Address(Integer id, String country, String region, String location, String details, User user) {
        super(id);
        this.country = country;
        this.region = region;
        this.location = location;
        this.details = details;
        this.user = user;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + this.getId() +
                "country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", location='" + location + '\'' +
                ", details='" + details + '\'' +
                ", userId=" + user.getId() +
                '}';
    }
}