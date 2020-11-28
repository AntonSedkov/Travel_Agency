package test.epam.tagency.model.service.impl;

import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.ClientPassport;
import by.epam.tagency.model.service.PassportService;
import by.epam.tagency.model.service.impl.PassportServiceImpl;
import org.easymock.EasyMock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class PassportServiceImplTest {
    PassportService service;

    @BeforeMethod
    public void setUp() {
        service = EasyMock.createNiceMock(PassportServiceImpl.class);
    }

    @Test
    public void testFindPassportsByIdUser() {
        List<ClientPassport> mockResult = new ArrayList<>();
        ClientPassport passportOne = new ClientPassport();
        mockResult.add(passportOne);
        ClientPassport passportTwo = new ClientPassport();
        mockResult.add(passportTwo);
        try {
            EasyMock.expect(service.findPassportsByIdUser(55555)).andReturn(mockResult);
            EasyMock.replay(service);
            List<ClientPassport> actual = service.findPassportsByIdUser(55555);
            assertEquals(actual, mockResult);
            EasyMock.verify(service);
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void testCreatePassportTrue() {
        try {
            EasyMock.expect(service.createPassport(1, "S", "N", "BD", "PN", "IN")).andReturn(true);
            EasyMock.replay(service);
            boolean actual = service.createPassport(1, "S", "N", "BD", "PN", "IN");
            assertTrue(actual);
            EasyMock.verify(service);
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void testCreatePassportFalse() {
        try {
            EasyMock.expect(service.createPassport(2, "S2", "N2", "BD2", "PN2", "IN2")).andReturn(false);
            EasyMock.replay(service);
            boolean actual = service.createPassport(2, "S2", "N2", "BD2", "PN2", "IN2");
            assertFalse(actual);
            EasyMock.verify(service);
        } catch (ServiceException e) {
            fail();
        }
    }

}