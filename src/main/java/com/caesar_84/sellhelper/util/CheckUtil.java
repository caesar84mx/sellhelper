package com.caesar_84.sellhelper.util;

import com.caesar_84.sellhelper.domain.User;
import com.caesar_84.sellhelper.util.exceptions.WrongUserException;

public final class CheckUtil {
    private CheckUtil() {}

    public static void checkUserIdConsistent(User user, int id) {
        if (user.isNew()) {
            user.setId(id);
        } else if (!user.getId().equals(id)) {
            throw new WrongUserException("You can't save the entity for another user!");
        }
    }
}
