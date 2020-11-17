package by.epam.tagency.model.dao;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.model.entity.User;

import java.util.List;

/**
 * Interface for {@link User} database interactions
 *
 * @author Anton Sedkov
 * @version 1.0
 */
public interface UserDao extends BaseDao<Integer, User> {

    /**
     * Search for status of the concrete user in database
     *
     * @param login reference to the login of the concrete user
     * @return status: {@code true} if user is activated; {@code false} if user is deactivated
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    boolean findStatusByLogin(String login) throws DaoException;

    /**
     * Search for the password of the concrete user in database
     *
     * @param login reference to the login of the concrete user
     * @return password or null if password is absent for this user
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    String findPassByLogin(String login) throws DaoException;

    /**
     * Search for the role of the concrete user in database
     *
     * @param login reference to the login of the concrete user
     * @return role or null if role is absent for this user
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    String findRoleByLogin(String login) throws DaoException;

    /**
     * Check for the unique value of the login
     *
     * @param login is the login to check
     * @return {@code true} if such login doesn't exist in database;
     * {@code false} such login exists in database
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    boolean isUniqueLogin(String login) throws DaoException;

    /**
     * Method provides one transaction of creation user in database:
     * at first, check the unique value of login,
     * then create the user with specified parameters,
     * in the end, if the user is {@code UserType.USER}, create the sheet for this user
     *
     * @param user     is {@code User} to create
     * @param password is password for user creation
     * @return {@code true} if user creation transaction has been completed; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     * @see by.epam.tagency.model.entity.UserType
     */
    boolean createNewUser(User user, String password) throws DaoException;

    /**
     * Deactivate the concrete user in database
     *
     * @param id reference to the identifier of the concrete user
     * @return {@code true} if user deactivation is successful; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    boolean deactivateUser(int id) throws DaoException;

    /**
     * Activate the concrete user in database
     *
     * @param id reference to the identifier of the concrete user
     * @return {@code true} if user activation is successful; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    boolean activateUser(int id) throws DaoException;

    /**
     * Activate the concrete user email in database
     *
     * @param login reference to the login of the concrete user
     * @return {@code true} if email activation is successful; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    boolean activateUserEmail(String login) throws DaoException;

    /**
     * Search for all users without the concrete user in database
     *
     * @param login reference to the login of the concrete user
     * @return {@link List} of users
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    List<User> findAllUsersWithoutCurrent(String login) throws DaoException;

    /**
     * Search for users quantity specified by role in database
     *
     * @param role is specified role
     * @return quantity of users or 0, if it is none such users
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    int countUsersByRole(String role) throws DaoException;

    /**
     * Changing password of the concrete user in database
     *
     * @param login    reference to the login of the concrete user
     * @param password is new password
     * @return {@code true} if password changing is successful; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    boolean changePassword(String login, String password) throws DaoException;

    /**
     * Changing email of the concrete user in database
     *
     * @param login reference to the login of the concrete user
     * @param email is new email
     * @return {@code true} if email changing is successful; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    boolean changeEmail(String login, String email) throws DaoException;

    /**
     * Changing username of the concrete user in database
     *
     * @param previousLogin reference to the login of the concrete user
     * @param newLogin      is new login
     * @return {@code true} if login changing is successful; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    boolean changeUsername(String previousLogin, String newLogin) throws DaoException;

    /**
     * Search for the concrete user identifier by the login in database
     *
     * @param login reference to the login of the concrete user
     * @return the concrete user identifier or 0, if such user doesn't exist in database
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    int findIdUserByLogin(String login) throws DaoException;

}