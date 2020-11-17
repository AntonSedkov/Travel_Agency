package by.epam.tagency.model.dao;

import by.epam.tagency.model.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Base interface for DAO pattern
 *
 * @param <K> relate to identifier type
 * @param <T> relate to current DAO object
 * @author Anton Sedkov
 * @version 1.0
 */
public interface BaseDao<K, T extends Entity> {
    Logger logger = LogManager.getLogger(BaseDao.class);

    /**
     * Close the resultSet if it exists
     *
     * @param resultSet reference to object which has to close
     */
    default void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    /**
     * Close the Statement if it exists
     *
     * @param statement reference to object which has to close
     */
    default void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

}