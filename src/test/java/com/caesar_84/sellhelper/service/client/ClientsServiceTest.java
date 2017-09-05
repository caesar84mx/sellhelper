package com.caesar_84.sellhelper.service.client;

import com.caesar_84.sellhelper.domain.Client;
import com.caesar_84.sellhelper.service.AbstractCommonCrudServiceTest;
import com.caesar_84.sellhelper.service.MockEntitiesForServiceTest;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class ClientsServiceTest extends AbstractCommonCrudServiceTest<ClientServiceImpl> {
    @Autowired
    @Override
    public void setService(ClientServiceImpl service) {
        super.setService(service);
    }

    @BeforeClass
    public static void configureMocks() {
        ArrayList<Client> list = new ArrayList<>();
        list.add(MockEntitiesForServiceTest.CLIENT_MOCK_EXPECTED_EXISTENT);

        setPreSavedMock(MockEntitiesForServiceTest.CLIENT_MOCK_PRESAVED);
        setExpectedMock(MockEntitiesForServiceTest.CLIENT_MOCK_EXPECTED);
        setExistingExpectedMock(MockEntitiesForServiceTest.CLIENT_MOCK_EXPECTED_EXISTENT);
        setExpectedMocksList(list);
    }
}
