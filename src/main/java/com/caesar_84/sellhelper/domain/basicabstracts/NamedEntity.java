package com.caesar_84.sellhelper.domain.basicabstracts;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Base class for entities having names.
 */

@MappedSuperclass
public class NamedEntity extends BaseEntity {
    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    public NamedEntity() {}

    public NamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String
    toString() {
        return "NamedEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
