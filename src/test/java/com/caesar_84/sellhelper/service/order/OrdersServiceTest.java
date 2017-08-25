package com.caesar_84.sellhelper.service.order;

import com.caesar_84.sellhelper.domain.Order;
import com.caesar_84.sellhelper.service.AbstractCommonCrudServiceTest;
import com.caesar_84.sellhelper.service.MockEntitiesForServiceTest;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class OrdersServiceTest extends AbstractCommonCrudServiceTest<OrderServiceImpl> {
    @Autowired
    @Override
    public void setService(OrderServiceImpl service) {
        super.setService(service);
    }

    @BeforeClass
    public static void configureMocks() {
        ArrayList<Order> list = new ArrayList<>();
        list.add(MockEntitiesForServiceTest.ORDER_MOCK_EXPECTED_EXISTENT);

        setExistingExpectedMock(MockEntitiesForServiceTest.ORDER_MOCK_EXPECTED_EXISTENT);
        setExpectedMock(MockEntitiesForServiceTest.ORDER_MOCK_EXPECTED);
        setPreSavedMock(MockEntitiesForServiceTest.ORDER_MOCK_PRESAVED);
        setExpectedMocksList(list);
    }
}