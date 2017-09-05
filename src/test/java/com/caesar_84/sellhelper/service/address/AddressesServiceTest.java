package com.caesar_84.sellhelper.service.address;

import com.caesar_84.sellhelper.domain.auxclasses.Address;
import com.caesar_84.sellhelper.service.AbstractCommonCrudServiceTest;
import com.caesar_84.sellhelper.service.MockEntitiesForServiceTest;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class AddressesServiceTest extends AbstractCommonCrudServiceTest<AddressServiceImpl> {
    @Autowired
    @Override
    public void setService(AddressServiceImpl service) {
        super.setService(service);
    }

    @BeforeClass
    public static void configureMocks() {
        ArrayList<Address> list = new ArrayList<>();
        list.add(MockEntitiesForServiceTest.ADDRESS_MOCK_EXPECTED_EXISTENT);

        setPreSavedMock(MockEntitiesForServiceTest.ADDRESS_MOCK_PRESAVED);
        setExpectedMock(MockEntitiesForServiceTest.ADDRESS_MOCK_EXPECTED);
        setExistingExpectedMock(MockEntitiesForServiceTest.ADDRESS_MOCK_EXPECTED_EXISTENT);
        setExpectedMocksList(list);
    }
}
