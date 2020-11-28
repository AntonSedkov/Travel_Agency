package test.epam.tagency.model.service.impl;

import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.SheetOperation;
import by.epam.tagency.model.service.OperationService;
import by.epam.tagency.model.service.impl.OperationServiceImpl;
import org.easymock.EasyMock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class OperationServiceImplTest {
    OperationService service;

    @BeforeMethod
    public void setUp() {
        service = EasyMock.createNiceMock(OperationServiceImpl.class);
    }

    @Test
    public void testFindOperationsByIdSheet() {
        List<SheetOperation> mockResult = new ArrayList<>();
        SheetOperation operation = new SheetOperation();
        mockResult.add(operation);
        try {
            EasyMock.expect(service.findOperationsByIdSheet(5)).andReturn(mockResult);
            EasyMock.replay(service);
            List<SheetOperation> actual = service.findOperationsByIdSheet(5);
            assertEquals(actual, mockResult);
            EasyMock.verify(service);
        } catch (ServiceException e) {
            fail();
        }
    }

}