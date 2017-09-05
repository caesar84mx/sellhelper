package com.caesar_84.sellhelper.service.good;

import com.caesar_84.sellhelper.domain.Good;
import com.caesar_84.sellhelper.service.AbstractCommonCrudServiceTest;
import com.caesar_84.sellhelper.service.MockEntitiesForServiceTest;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class GoodsServiceTest extends AbstractCommonCrudServiceTest<GoodServiceImpl> {
    @Autowired
    @Override
    public void setService(GoodServiceImpl service) {
        super.setService(service);
    }

    @BeforeClass
    public static void configureMocks(){
        ArrayList<Good> list = new ArrayList<>();
        list.add(MockEntitiesForServiceTest.GOOD_MOCK_EXPECTED_EXISTENT);

        setPreSavedMock(MockEntitiesForServiceTest.GOOD_MOCK_PRESAVED);
        setExpectedMock(MockEntitiesForServiceTest.GOOD_MOCK_EXPECTED);
        setExistingExpectedMock(MockEntitiesForServiceTest.GOOD_MOCK_EXPECTED_EXISTENT);
        setExpectedMocksList(list);
    }
}
