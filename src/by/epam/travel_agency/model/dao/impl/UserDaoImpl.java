package by.epam.travel_agency.model.dao.impl;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.model.connection.ConnectionPool;
import by.epam.travel_agency.model.dao.ColumnName;
import by.epam.travel_agency.model.dao.StatementSql;
import by.epam.travel_agency.model.dao.UserDao;
import by.epam.travel_agency.model.entity.User;

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
    public boolean findStatusByLogin(String login) throws DaoException {
        boolean result = false;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.FIND_STATUS_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getBoolean(ColumnName.COLUMN_STATUS);
            }
        } catch (SQLException ex) {
            throw new DaoException("Exception of finding user status by login. ", ex);
        }
        return result;
    }

    @Override
    public String findPassByLogin(String login) throws DaoException {
        String resultPass = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.FIND_PASS_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                resultPass = resultSet.getString(ColumnName.COLUMN_PASSWORD);
            }
        } catch (SQLException ex) {
            throw new DaoException("Exception of finding password by login. ", ex);
        }
        return resultPass;
    }

    @Override
    public String findRoleByLogin(String login) throws DaoException {
        String resultRole = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.FIND_ROLE_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                resultRole = resultSet.getString(ColumnName.COLUMN_ROLE);
            }
        } catch (SQLException ex) {
            throw new DaoException("Exception of finding role by login. ", ex);
        }
        return resultRole;
    }

    @Override
    public boolean isUniqueLogin(String login) throws DaoException {
        boolean result = false;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.CHECK_LOGIN_UNIQUE)) {
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
    public boolean createNewUser(User user, String encryptedPassword) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.CREATE_USER)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, encryptedPassword);
            preparedStatement.setString(3, user.getEmail());
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Exception of creating new User in database", e);
        }
        return result;
    }

}