package test.epam.travel_agency.util;

import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.util.Encryption;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EncryptionTest {
    String oneString = "Hello_Java!!!";

    @Test
    public void testGetSaltedHashDifferentForOne() {
        try {
            String actualOne = Encryption.getSaltedHash(oneString);
            System.out.println(actualOne);
            String actualTwo = Encryption.getSaltedHash(oneString);
            System.out.println(actualTwo);
            assertNotEquals(actualOne, actualTwo);
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test(dependsOnMethods = "testGetSaltedHashDifferentForOne")
    public void testCheckPassword() {
        try {
            String encryptedString = Encryption.getSaltedHash(oneString);
            boolean actual = Encryption.checkPassword(oneString, encryptedString);
            assertTrue(actual);
        } catch (ServiceException e) {
            fail();
        }
    }

}