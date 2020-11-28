package by.epam.tagency.model.service;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.User;
import by.epam.tagency.model.entity.UserType;

import java.util.List;
import java.util.Map;

/**
 * Interface for {@link User} service actions
 *
 * @author Anton Sedkov
 * @version 1.0
 */
public interface UserService {

    /**
     * Result of validation user data: check active status and then comparing entered and database passwords for identity
     *
     * @param enterLogin    is entered username
     * @param enterPassword is entered password
     * @return {@code true} if data validation is successful; {@code false} otherwise
     * @throws ServiceException when {@link DaoException} has happened or wrong password value for this login
     */
    boolean checkLoginData(String enterLogin, String enterPassword) throws ServiceException;

    /**
     * Result of validation parameters and creation new user
     *
     * @param enterLogin is username
     * @param enterPass  is password
     * @param enterEmail is email
     * @param enterRole  is {@code UserType}
     * @return {@code true} if user creation is successful; {@code false} otherwise
     * @throws ServiceException when {@link DaoException} has happened
     * @see UserType
     */
    boolean createNewUser(String enterLogin, String enterPass, String enterEmail, String enterRole) throws ServiceException;

    /**
     * Search for the role of the concrete user
     *
     * @param enterLogin reference to the login of the concrete user
     * @return {@code UserType} for this user
     * @throws ServiceException when {@link DaoException} has happened or incorrect {@code UserType}
     * @see UserType
     */
    UserType findRoleByUsername(String enterLogin) throws ServiceException;

    /**
     * Result of validation parameter and activation the concrete user
     *
     * @param id reference to the identifier of the concrete user
     * @return {@code true} if user activation is successful; {@code false} otherwise
     * @throws ServiceException when {@link DaoException} has happened or wrong format of incoming parameter
     */
    boolean activateUser(String id) throws ServiceException;

    /**
     * Result of validation parameter and deactivation the concrete user
     *
     * @param id reference to the identifier of the concrete user
     * @return {@code true} if user deactivation is successful; {@code false} otherwise
     * @throws ServiceException when {@link DaoException} has happened or wrong format of incoming parameter
     */
    boolean deactivateUser(String id) throws ServiceException;

    /**
     * Result of validation parameter and activation the concrete user email
     *
     * @param login reference to the login of the concrete user
     * @return {@code true} if email activation is successful; {@code false} otherwise
     * @throws ServiceException when {@link DaoException} has happened or wrong format of incoming parameter
     */
    boolean activateUserEmail(String login) throws ServiceException;

    /**
     * Search for all users without the concrete user
     *
     * @param login reference to the login of the concrete user
     * @return {@link List} of users
     * @throws ServiceException when {@link DaoException} has happened or wrong format of incoming parameter
     */
    List<User> findAllUsersWithoutCurrent(String login) throws ServiceException;

    /**
     * Search for specified role and quantity of users for this role
     *
     * @return {@link Map} of roles and quantities where key is {@code String} role and value is {@code Integer} quantity
     * @throws ServiceException when {@link DaoException} has happened
     */
    Map<String, Integer> countUsersQuantityByRole() throws ServiceException;

    /**
     * Result of validation parameters, check current password and changing password of the concrete user
     *
     * @param login            reference to the login of the concrete user
     * @param previousPassword is previous password
     * @param newPassword      is new password
     * @return {@code true} if password changing is successful; {@code false} otherwise
     * @throws ServiceException when {@link DaoException} has happened or incorrect current password
     */
    boolean changePassword(String login, String previousPassword, String newPassword) throws ServiceException;

    /**
     * Result of validation parameters and changing email of the concrete user
     *
     * @param login reference to the login of the concrete user
     * @param email is new email
     * @return {@code true} if email changing is successful; {@code false} otherwise
     * @throws ServiceException when {@link DaoException} has happened
     */
    boolean changeEmail(String login, String email) throws ServiceException;

    /**
     * Result of validation parameters and changing username of the concrete user in database
     *
     * @param previousLogin reference to the login of the concrete user
     * @param newLogin      is new login
     * @return {@code true} if login changing is successful; {@code false} otherwise
     * @throws ServiceException when {@link DaoException} has happened
     */
    boolean changeUsername(String previousLogin, String newLogin) throws ServiceException;

    /**
     * Result of validation parameter and search for the concrete user identifier by the login
     *
     * @param login reference to the login of the concrete user
     * @return the concrete user identifier
     * @throws ServiceException when {@link DaoException} has happened or wrong identifier for this login
     */
    int findIdUserByLogin(String login) throws ServiceException;

}