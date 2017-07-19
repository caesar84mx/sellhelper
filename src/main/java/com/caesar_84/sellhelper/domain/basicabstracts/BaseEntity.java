package com.caesar_84.sellhelper.domain.basicabstracts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.hateoas.Identifiable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.MappedSuperclass;

/**
 * A base entity class.
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class BaseEntity extends AbstractPersistable<Integer> implements Identifiable<Integer> {
    public BaseEntity() {}

    public BaseEntity(Integer id) { setId(id); }

    @JsonIgnore
    public boolean isNew() { return super.isNew(); }
}
