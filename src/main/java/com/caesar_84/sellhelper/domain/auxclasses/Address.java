package com.caesar_84.sellhelper.domain.auxclasses;

import com.caesar_84.sellhelper.domain.basicabstracts.BaseEntity;

public class Address extends BaseEntity {
    private String country;

    private String region;

    private String location;

    private String details;

    public Address() {}

    public Address(String country, String region, String location, String details) {
        this(null, country, region, location, details);
    }

    public Address(Integer id, String country, String region, String location, String details) {
        super(id);
        this.country = country;
        this.region = region;
        this.location = location;
        this.details = details;
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
}
