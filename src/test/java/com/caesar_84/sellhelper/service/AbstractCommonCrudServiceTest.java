package com.caesar_84.sellhelper.service;

import com.caesar_84.sellhelper.AbstractAncestorTest;
import com.caesar_84.sellhelper.domain.basicabstracts.BaseEntity;
import org.junit.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("unchecked")
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"}, config = @SqlConfig(encoding = "UTF-8"))
public abstract class AbstractCommonCrudServiceTest<T extends CommonCrudService> extends AbstractAncestorTest {
    private T service;

    private static BaseEntity preSavedMock;

    private static BaseEntity expectedMock;

    private static BaseEntity existingExpectedMock;

    private static List<? extends BaseEntity> expectedMocksList;

    private Integer userId = MockEntitiesForServiceTest.USER_ID;

    @Test
    @Transactional
    public void saveOrUpdate() throws Exception {
        BaseEntity savedMock = service.saveOrUpdate(preSavedMock, userId);
        assertEquals("Entities do not match! Expected: " + expectedMock +
                ", got: " + savedMock,
                expectedMock, savedMock);
    }

    @Test
    public void get() throws Exception {
        BaseEntity result = service.get(1, userId);
        assertEquals("Entities do not match! Expected: " + existingExpectedMock +
                ", got: " + result,
                existingExpectedMock, result);
    }

    @Test
    @WithMockUser(username = "brutus.84@word.ir", password = "passcode", roles = "USER")
    @Transactional
    public void delete() throws Exception {
        Integer expId = service.saveOrUpdate(expectedMock,userId).getId();
        assertTrue("Failed to delete entity, expected id: " + expId +
                " actual id: " + expectedMock.getId(), service.delete(expectedMock.getId(), userId));
    }

    @Test
    public void getAll() throws Exception {
        assertArrayEquals("Lists of entities do not match!", expectedMocksList.toArray(),
                service.getAll(userId).toArray());
    }

    public T getService() { return service; }

    public Integer getUserId() { return userId; }

    public BaseEntity getPreSavedMock() {
        return preSavedMock;
    }

    public static void setPreSavedMock(BaseEntity preSavedMock) {
        AbstractCommonCrudServiceTest.preSavedMock = preSavedMock;
    }

    public BaseEntity getExistingExpectedMock() {
        return existingExpectedMock;
    }

    public static void setExistingExpectedMock(BaseEntity existingExpectedMock) {
        AbstractCommonCrudServiceTest.existingExpectedMock = existingExpectedMock;
    }

    public BaseEntity getExpectedMock() {
        return expectedMock;
    }

    public static void setExpectedMock(BaseEntity expectedMock) {
        AbstractCommonCrudServiceTest.expectedMock = expectedMock;
    }

    public List<? extends BaseEntity> getExpectedMocksList() {
        return expectedMocksList;
    }

    public static void setExpectedMocksList(List<? extends BaseEntity> expectedMocksList) {
        AbstractCommonCrudServiceTest.expectedMocksList = expectedMocksList;
    }

    public void setService(T service) {
        this.service = service;
    }
}