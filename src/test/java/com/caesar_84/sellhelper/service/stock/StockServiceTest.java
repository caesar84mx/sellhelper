package com.caesar_84.sellhelper.service.stock;

import com.caesar_84.sellhelper.domain.StockItem;
import com.caesar_84.sellhelper.service.AbstractCommonCrudServiceTest;
import com.caesar_84.sellhelper.service.MockEntitiesForServiceTest;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class StockServiceTest extends AbstractCommonCrudServiceTest<StockServiceImpl> {
    @Autowired
    @Override
    public void setService(StockServiceImpl service) {
        super.setService(service);
    }

    @BeforeClass
    public static void configureMocks() {
        ArrayList<StockItem> list = new ArrayList<>();
        list.add(MockEntitiesForServiceTest.STOCK_ITEM_MOCK_EXPECTED_EXISTENT);

        setPreSavedMock(MockEntitiesForServiceTest.STOCK_ITEM_MOCK_PRESAVED);
        setExpectedMock(MockEntitiesForServiceTest.STOCK_ITEM_MOCK_EXPECTED);
        setExistingExpectedMock(MockEntitiesForServiceTest.STOCK_ITEM_MOCK_EXPECTED_EXISTENT);
        setExpectedMocksList(list);
    }
}