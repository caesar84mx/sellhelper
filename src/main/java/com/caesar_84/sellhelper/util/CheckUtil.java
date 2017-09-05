package com.caesar_84.sellhelper.util;

import com.caesar_84.sellhelper.domain.Order;
import com.caesar_84.sellhelper.domain.User;
import com.caesar_84.sellhelper.domain.auxclasses.OrderStatus;
import com.caesar_84.sellhelper.domain.basicabstracts.BaseEntity;
import com.caesar_84.sellhelper.util.exceptions.WrongStatusExceprion;
import com.caesar_84.sellhelper.util.exceptions.WrongUserException;

public final class CheckUtil {
    private CheckUtil() {}

    public static void checkUserIdConsistent(Integer userId, int id) {
        if (!userId.equals(id)) {
            throw new WrongUserException("You can't save the entity for another user!");
        }
    }

    public static void checkOrderStatus(Order order, OrderStatus status) {
        if (order.getStatus() != status) {
            throw new WrongStatusExceprion("Wrong status!");
        }
    }

    public static void checkNotNull(BaseEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("The entity is null!");
        }
    }
}
