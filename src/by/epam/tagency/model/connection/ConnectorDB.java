package by.epam.tagency.model.connection;

import by.epam.tagency.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectorDB {
    private static Logger logger = LogManager.getLogger(ConnectorDB.class);
    private static final String PROP_DATABASE_DRIVER = "db.driver";
    private static final String PROP_DATABASE_URL = "db.url";
    private static final String PROP_PATH = "configuration/database.properties";

    private static final Properties properties = new Properties();
    private static final String DATABASE_URL;

    static {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(PROP_PATH);
            properties.load(inputStream);
            if (inputStream != null) {
                inputStream.close();
            }
            String driverName = (String) properties.get(PROP_DATABASE_DRIVER);
            Class.forName(driverName);
            logger.info("Driver MySql has been loaded. ");
        } catch (IOException e) {
            logger.fatal("Can't find properties file. ", e);
            throw new RuntimeException("Can't find properties file. ", e);
        } catch (ClassNotFoundException e) {
            logger.fatal("Can't register driver. ", e);
            throw new RuntimeException("Can't register driver. ", e);
        }
        DATABASE_URL = (String) properties.get(PROP_DATABASE_URL);
    }

    static Connection getConnection() throws DaoException {
        try {
            return DriverManager.getConnection(DATABASE_URL, properties);
        } catch (SQLException e) {
            throw new DaoException("Problem with creating Connection. ", e);
        }
    }

}