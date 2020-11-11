package test.epam.tagency.util;

import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.util.EncryptionManager;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EncryptionManagerTest {
    String oneString = "Hello_Java!!!";

    @Test
    public void testGetSaltedHashDifferentForOne() {
        try {
            String actualOne = EncryptionManager.getSaltedHash(oneString);
            System.out.println(actualOne);
            String actualTwo = EncryptionManager.getSaltedHash(oneString);
            System.out.println(actualTwo);
            assertNotEquals(actualOne, actualTwo);
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test(dependsOnMethods = "testGetSaltedHashDifferentForOne")
    public void testCheckPassword() {
        try {
            String encryptedString = EncryptionManager.getSaltedHash(oneString);
            boolean actual = EncryptionManager.checkPassword(oneString, encryptedString);
            assertTrue(actual);
        } catch (ServiceException e) {
            fail();
        }
    }

}