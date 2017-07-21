package com.caesar_84.sellhelper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;
import java.util.Objects;

public class LoggedUser extends User {
    private static Integer id;

    public LoggedUser(com.caesar_84.sellhelper.domain.User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(),
                true, true, true,
                Collections.singletonList(user.getRole()));
        id = user.getId();
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

    public static Integer id() {
        return id;
    }
}
