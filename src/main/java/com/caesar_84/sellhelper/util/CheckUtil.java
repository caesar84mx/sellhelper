package com.caesar_84.sellhelper.util;

import com.caesar_84.sellhelper.domain.Order;
import com.caesar_84.sellhelper.domain.User;
import com.caesar_84.sellhelper.domain.auxclasses.OrderStatus;
import com.caesar_84.sellhelper.domain.basicabstracts.BaseEntity;
import com.caesar_84.sellhelper.util.exceptions.WrongStatusExceprion;
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

    public static boolean onlyStatusChanged(Order order1, Order order2) {

        return order1.getId().equals(order2.getId()) &&
                order1.getClient().equals(order2.getClient()) &&
                order1.getShipmentAddress().equals(order2.getShipmentAddress()) &&
                order1.getGoods().entrySet().containsAll(order2.getGoods().entrySet()) &&
                order1.getUser().equals(order2.getUser()) &&
                order1.getCreated().equals(order2.getCreated()) &&
                order1.getStatus() != order2.getStatus();
    }
}
