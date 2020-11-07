package by.epam.tagency.util;

import by.epam.tagency.exception.ServiceException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class EncryptionManager {
    private static final int ITERATIONS = 20 * 1000;
    private static final int SALT_LENGTH = 32;
    private static final int DESIRED_KEY_LENGTH = 256;
    private static final int ENCRYPTION_PARTS = 2;
    private static final String ENCRYPTION_SALT_ALGORITHM = "SHA1PRNG";
    private static final String ENCRYPTION_KEY_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final String TWO_SLASHES_FOR_SCREENING = "\\";
    private static final String ENCRYPTION_DELIMITER = "$";

    private EncryptionManager() {
    }

    public static String getSaltedHash(String password) throws ServiceException {
        String result;
        try {
            byte[] salt = SecureRandom.getInstance(ENCRYPTION_SALT_ALGORITHM).generateSeed(SALT_LENGTH);
            result = Base64.encodeBase64String(salt) + ENCRYPTION_DELIMITER + hash(password, salt);
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("Wrong algorithm for salt", e);
        }
        return result;
    }

    public static boolean checkPassword(String password, String passwordDB) throws ServiceException {
        String[] saltAndPassword = passwordDB.split(TWO_SLASHES_FOR_SCREENING + ENCRYPTION_DELIMITER);
        if (saltAndPassword.length != ENCRYPTION_PARTS) {
            throw new ServiceException("The password in the database has the form: 'salt$hash'");
        }
        String hashOfInput = hash(password, Base64.decodeBase64(saltAndPassword[0]));
        return hashOfInput.equals(saltAndPassword[1]);
    }

    private static String hash(String password, byte[] salt) throws ServiceException {
        String result;
        if (password == null || password.length() == 0) {
            throw new ServiceException("Empty password is not supported.");
        }
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ENCRYPTION_KEY_ALGORITHM);
            SecretKey key = factory.generateSecret(new PBEKeySpec(
                    password.toCharArray(), salt, ITERATIONS, DESIRED_KEY_LENGTH)
            );
            result = Base64.encodeBase64String(key.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("Wrong algorithm for key encryption", e);
        } catch (InvalidKeySpecException e) {
            throw new ServiceException("Wrong specification for key encryption", e);
        }
        return result;
    }

}