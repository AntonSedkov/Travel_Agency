package by.epam.travel_agency.util.mail;

import by.epam.travel_agency.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailSessionConfigurer {
    public static Logger logger = LogManager.getLogger(MailSessionConfigurer.class);
    public static final String KEY_MAIL_USER = "mail.user.name";
    public static final String KEY_MAIL_PASSWORD = "mail.user.password";
    private static final String RESOURCE_PATH = "configuration/mail.properties";

    public static Session createSession() throws ServiceException {
        Properties configProperties = new Properties();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(RESOURCE_PATH);
            configProperties.load(inputStream);
            if (inputStream != null) {
                inputStream.close();
            }
            logger.info("Properties for mail sender has been loaded.");
        } catch (IOException e) {
            logger.fatal("Can't find properties file. ", e);
            throw new ServiceException("Can't find properties file. ", e);
        }
        String userName = configProperties.getProperty(KEY_MAIL_USER);
        String userPassword = configProperties.getProperty(KEY_MAIL_PASSWORD);
        return Session.getDefaultInstance(configProperties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, userPassword);
                    }
                });
    }

}