package com.caesar_84.sellhelper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;
import java.util.Objects;

public class LoggedUser extends User {
    private com.caesar_84.sellhelper.domain.User referredUser;

    public LoggedUser(com.caesar_84.sellhelper.domain.User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(),
                true, true, true,
                Collections.singletonList(user.getRole()));
        referredUser = user;
    }

    private static LoggedUser safeGet() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }

        Object user = authentication.getPrincipal();
        return (user instanceof LoggedUser) ? (LoggedUser) user : null;
    }

    public static LoggedUser get() {
        LoggedUser user = safeGet();
        Objects.requireNonNull(user, "User not found");
        return user;
    }

    public com.caesar_84.sellhelper.domain.User getReferredUser() {
        return referredUser;
    }

    public Integer getId() { return referredUser.getId(); }

    public static Integer id() {
        return get().referredUser.getId();
    }
}
