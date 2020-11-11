package by.epam.tagency.model.dao.impl;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.model.connection.ConnectionPool;
import by.epam.tagency.model.dao.ColumnName;
import by.epam.tagency.model.dao.QuerySql;
import by.epam.tagency.model.dao.UserDao;
import by.epam.tagency.model.entity.User;
import by.epam.tagency.model.entity.UserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static Logger logger = LogManager.getLogger(UserDaoImpl.class);
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
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.FIND_STATUS_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getBoolean(ColumnName.STATUS);
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
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.FIND_PASS_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                resultPass = resultSet.getString(ColumnName.PASSWORD);
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
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.FIND_ROLE_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                resultRole = resultSet.getString(ColumnName.ROLE);
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
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.CHECK_LOGIN_UNIQUE)) {
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
        boolean result = false;
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement psCheckUnique = connection.prepareStatement(QuerySql.CHECK_LOGIN_UNIQUE);
                 PreparedStatement psCreateUser = connection.prepareStatement(QuerySql.CREATE_USER, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement psCreateSheet = connection.prepareStatement(QuerySql.CREATE_SHEET_WITH_ID_USER)) {
                connection.setAutoCommit(false);
                psCheckUnique.setString(1, user.getLogin());
                ResultSet rsCheckUnique = psCheckUnique.executeQuery();
                if (rsCheckUnique.next()) {
                    result = rsCheckUnique.getInt(1) == 0;
                    logger.info("Username unique is " + result);
                }
                if (result) {
                    psCreateUser.setString(1, user.getLogin());
                    psCreateUser.setString(2, encryptedPassword);
                    psCreateUser.setString(3, user.getEmail());
                    psCreateUser.setString(4, user.getRole().toString().toLowerCase());
                    result = psCreateUser.executeUpdate() > 0;
                    logger.info("User creation is " + result);
                    ResultSet rsIdUser = psCreateUser.getGeneratedKeys();
                    if (user.getRole().equals(UserType.USER) && result && rsIdUser.next()) {
                        int idUser = rsIdUser.getInt(1);
                        psCreateSheet.setInt(1, idUser);
                        result = psCreateSheet.executeUpdate() > 0;
                        logger.info("User sheet creation is " + result);
                    }
                    if (result) {
                        connection.commit();
                    }
                }
            } catch (SQLException e) {
                connection.rollback();
                throw new DaoException("Exception of creating new User in database", e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DaoException("Connection exception in transaction of creating new user in database", e);
        }
        return result;
    }

    @Override
    public boolean deactivateUser(int id) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.DEACTIVATE_USER)) {
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Exception of deactivating User in database", e);
        }
        return result;
    }

    @Override
    public boolean activateUser(int id) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.ACTIVATE_USER)) {
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Exception of activating User in database", e);
        }
        return result;
    }

    @Override
    public boolean activateUserEmail(String login) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.ACTIVATE_USER_EMAIL)) {
            preparedStatement.setString(1, login);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Exception of activating User email in database", e);
        }
        return result;
    }

    @Override
    public List<User> findAllUsersWithoutCurrent(String login) throws DaoException {
        List<User> result = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.FIND_ALL_USERS_WITHOUT_CURRENT)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(ColumnName.ID_USER));
                user.setLogin(resultSet.getString(ColumnName.LOGIN));
                user.setEmail(resultSet.getString(ColumnName.EMAIL));
                user.setRole(UserType.valueOf(resultSet.getString(ColumnName.ROLE).toUpperCase()));
                user.setStatus(resultSet.getBoolean(ColumnName.STATUS));
                user.setApprovedEmail(resultSet.getBoolean(ColumnName.EMAIL_APPROVED));
                result.add(user);
            }
        } catch (SQLException ex) {
            throw new DaoException("Exception of finding all users.", ex);
        }
        return result;
    }

    @Override
    public int countUsersByRole(String role) throws DaoException {
        int result = 0;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.COUNT_USERS_BY_ROLE)) {
            preparedStatement.setString(1, role);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            throw new DaoException("Exception of counting users by role.", ex);
        }
        return result;
    }

    @Override
    public boolean changePassword(String login, String password) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.CHANGE_PASSWORD)) {
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, login);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Exception of changing password in database", e);
        }
        return result;
    }

    @Override
    public boolean changeEmail(String login, String email) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.CHANGE_EMAIL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, login);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Exception of changing email in database", e);
        }
        return result;
    }

    @Override
    public boolean changeUsername(String previousLogin, String newLogin) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.CHANGE_LOGIN)) {
            preparedStatement.setString(1, newLogin);
            preparedStatement.setString(2, previousLogin);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Exception of changing login in database", e);
        }
        return result;
    }

    @Override
    public int findIdUserByLogin(String login) throws DaoException {
        int resultId = 0;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.FIND_ID_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                resultId = resultSet.getInt(ColumnName.ID_USER);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception of finding id user by login. ", e);
        }
        return resultId;
    }

}