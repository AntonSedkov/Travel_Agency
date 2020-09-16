package by.epam.travel_agency.model.dao.impl;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.model.connection.ConnectionPool;
import by.epam.travel_agency.model.dao.ColumnName;
import by.epam.travel_agency.model.dao.StatementSql;
import by.epam.travel_agency.model.dao.UserDao;
import by.epam.travel_agency.model.entity.User;
import by.epam.travel_agency.model.entity.UserType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private static final UserDaoImpl INSTANCE = new UserDaoImpl();
    private static ConnectionPool pool = ConnectionPool.INSTANCE;

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public User findByLogin(String login) throws DaoException {
        User resultUser = new User();
        resultUser.setStatus(false);
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.SQL_FIND_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultUser.setLogin(login);
                resultUser.setPassword(resultSet.getString(ColumnName.COLUMN_PASSWORD));
                UserType userType = UserType.valueOf(resultSet.getString(ColumnName.COLUMN_ROLE).toUpperCase());
                resultUser.setRole(userType);
                resultUser.setStatus(resultSet.getBoolean(ColumnName.COLUMN_STATUS));
            }
        } catch (SQLException ex) {
            throw new DaoException("Exception of finding user by login. ", ex);
        }
        return resultUser;
    }

    @Override
    public boolean isUniqueLogin(String login) throws DaoException {
        boolean result = false;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.SQL_CHECK_LOGIN_UNIQUE)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1) == 0;
            }
        } catch (SQLException ex) {
            throw new DaoException("Exception of checking unique value of user login", ex);
        }
        return result;
    }

    @Override
    public boolean create(User entity) throws DaoException {
        boolean result = false;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.SQL_CREATE_USER)) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getEmail());
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Exception of creating new User in database", e);
        }
        return result;
    }

}