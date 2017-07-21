package com.caesar_84.sellhelper.domain;

import com.caesar_84.sellhelper.domain.auxclasses.Roles;
import com.caesar_84.sellhelper.domain.basicabstracts.NamedEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email", name = "user_uidx_id"),
        @UniqueConstraint(columnNames = "parent_id", name = "user_uidx_parent")
})
public class User extends NamedEntity {
    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    @Column(name = "last_name", nullable = false)
    @NotBlank
    private String lastName;

    @Column(name = "email", nullable = false)
    @NotBlank
    @Email
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Length(min = 5)
    private String password;

    @Column(name = "parent_id")
    private Integer parentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Roles role;

    @Column(name = "enabled", columnDefinition = "boolean default true")
    private boolean enabled;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    private LocalDateTime registered = LocalDateTime.now();

    @Column(name = "modified", columnDefinition = "timestamp default now()")
    private LocalDateTime modified = LocalDateTime.now();

    public User() {}

    public User(Integer id, String name, String lastName, String email, String password,
                Integer parentId, Roles role, boolean enabled, LocalDateTime registered,
                LocalDateTime modified) {
        super(id, name);
        this.lastName = lastName;
        this.email = email;
        this.password = ENCODER.encode(password);
        this.parentId = parentId;
        this.role = role;
        this.enabled = enabled;
        this.registered = registered;
        this.modified = modified;
    }

    public User(String name, String lastName, String email, String password, Integer parentId,
                Roles role, boolean enabled, LocalDateTime registered, LocalDateTime modified) {
        this(null, name, lastName, email, password, parentId, role, enabled, registered,
                modified);
    }

    public User(String name, String lastName, String email, String password, Integer parentId,
                Roles role) {
        this(name, lastName, email, password, parentId, role, true, LocalDateTime.now(),
                LocalDateTime.now());
    }

    public User(String name, String lastName, String email, String password, Integer parentId,
                Roles role, LocalDateTime registered) {
        this(name, lastName, email, password, parentId, role, true, registered,
                LocalDateTime.now());
    }

    public User(User user) {
        this(user.getId(), user.getName(), user.getLastName(), user.getEmail(), user.getPassword(),
                user.getParentId(), user.getRole(), user.isEnabled(), user.getRegistered(),
                user.getModified());
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = ENCODER.encode(password);
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}