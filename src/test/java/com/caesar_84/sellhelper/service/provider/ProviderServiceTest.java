package com.caesar_84.sellhelper.service.provider;

import com.caesar_84.sellhelper.domain.GoodsProvider;
import com.caesar_84.sellhelper.service.AbstractCommonCrudServiceTest;
import com.caesar_84.sellhelper.service.MockEntitiesForServiceTest;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class ProviderServiceTest extends AbstractCommonCrudServiceTest<ProviderServiceImpl> {
    @Autowired
    @Override
    public void setService(ProviderServiceImpl service) {
        super.setService(service);
    }

    @BeforeClass
    public static void configureMocks(){
        ArrayList<GoodsProvider> list = new ArrayList<>();
        list.add(MockEntitiesForServiceTest.PROVIDER_MOCK_EXPECTED_EXISTENT);

        setPreSavedMock(MockEntitiesForServiceTest.PROVIDER_MOCK_PRESAVED);
        setExpectedMock(MockEntitiesForServiceTest.PROVIDER_MOCK_EXPECTED);
        setExistingExpectedMock(MockEntitiesForServiceTest.PROVIDER_MOCK_EXPECTED_EXISTENT);
        setExpectedMocksList(list);
    }
}
