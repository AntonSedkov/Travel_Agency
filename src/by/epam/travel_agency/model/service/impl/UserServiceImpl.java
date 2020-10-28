package by.epam.travel_agency.model.service.impl;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.dao.UserDao;
import by.epam.travel_agency.model.dao.impl.UserDaoImpl;
import by.epam.travel_agency.model.entity.User;
import by.epam.travel_agency.model.entity.UserType;
import by.epam.travel_agency.model.service.UserService;
import by.epam.travel_agency.util.EncryptionManager;
import by.epam.travel_agency.validator.GeneralValidator;
import by.epam.travel_agency.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    private static Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean checkLoginData(String enterLogin, String enterPassword) throws ServiceException {
        boolean result = false;
        if (UserValidator.isValidLogin(enterLogin) && UserValidator.isValidPassword(enterPassword)) {
            try {
                UserDao dao = UserDaoImpl.getInstance();
                boolean userStatus = dao.findStatusByLogin(enterLogin.strip());
                if (userStatus) {
                    String encryptedPassword = dao.findPassByLogin(enterLogin.strip());
                    if (encryptedPassword == null || encryptedPassword.isBlank()) {
                        throw new ServiceException("Password from database is Empty for login " + enterLogin);
                    }
                    result = EncryptionManager.checkPassword(enterPassword.strip(), encryptedPassword);
                    logger.info("Checked password successfully");
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public boolean createNewUser(String enterLogin, String enterPass, String enterEmail, String enterRole) throws ServiceException {
        boolean result = false;
        UserDao dao = UserDaoImpl.getInstance();
        if (UserValidator.isValidLogin(enterLogin) && UserValidator.isValidPassword(enterPass)
                && UserValidator.isValidEmail(enterEmail) && UserValidator.isValidRole(enterRole)) {
            User newUser = new User(enterLogin.strip(), enterEmail.strip(), enterRole.strip());
            String encryptedPassword = EncryptionManager.getSaltedHash(enterPass.strip());
            try {
                result = dao.createNewUser(newUser, encryptedPassword);
                logger.info("Created new user - client");
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public UserType findRoleByUsername(String enterLogin) throws ServiceException {
        UserType role = UserType.USER;
        if (UserValidator.isValidLogin(enterLogin)) {
            try {
                String strRole = UserDaoImpl.getInstance().findRoleByLogin(enterLogin.strip());
                if (strRole == null || strRole.isBlank()) {
                    throw new ServiceException("Role from database is empty for login " + enterLogin);
                }
                role = UserType.valueOf(strRole.toUpperCase());
                logger.info("User type has been defined successfully");

            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return role;
    }

    @Override
    public boolean activateUser(String id) throws ServiceException {
        boolean result = false;
        if (GeneralValidator.isDigitValue(id)) {
            try {
                int idInt = Integer.parseInt(id.strip());
                UserDaoImpl dao = UserDaoImpl.getInstance();
                result = dao.activateUser(idInt);
                logger.info("User with id: " + id + " has been activated.");
            } catch (NumberFormatException e) {
                throw new ServiceException("Incoming ID is wrong format - not an integer", e);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public boolean deactivateUser(String id) throws ServiceException {
        boolean result = false;
        if (GeneralValidator.isDigitValue(id)) {
            try {
                int idInt = Integer.parseInt(id.strip());
                UserDaoImpl dao = UserDaoImpl.getInstance();
                result = dao.deactivateUser(idInt);
                logger.info("User with id: " + id + " has been deactivated.");
            } catch (NumberFormatException e) {
                throw new ServiceException("Incoming ID is wrong format - not an integer", e);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public boolean activateUserEmail(String login) throws ServiceException {
        boolean result = false;
        if (UserValidator.isValidLogin(login)) {
            UserDaoImpl dao = UserDaoImpl.getInstance();
            try {
                result = dao.activateUserEmail(login.strip());
                if (!result) {
                    throw new ServiceException("Attempt to activate email with incorrect login");
                }
                logger.info("Email: " + login + " has been activated.");
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public List<User> findAllUsersWithoutCurrent(String login) throws ServiceException {
        List<User> result;
        UserDaoImpl dao = UserDaoImpl.getInstance();
        try {
            result = dao.findAllUsersWithoutCurrent(login);
            logger.info("Find all users.");
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean changePassword(String login, String previousPassword, String newPassword) throws ServiceException {
        boolean result = false;
        if (UserValidator.isValidLogin(login) && UserValidator.isValidPassword(previousPassword)
                && UserValidator.isValidPassword(newPassword)) {
            UserDaoImpl dao = UserDaoImpl.getInstance();
            try {
                String encryptedDBPass = dao.findPassByLogin(login.strip());
                if (encryptedDBPass == null || encryptedDBPass.isBlank()) {
                    throw new ServiceException("Password from database is Empty for login " + login);
                }
                if (EncryptionManager.checkPassword(previousPassword.strip(), encryptedDBPass)) {
                    String encryptedNewPass = EncryptionManager.getSaltedHash(newPassword.strip());
                    result = dao.changePassword(login.strip(), encryptedNewPass);
                    logger.info("Password for login: " + login + " is changed: " + result);
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public boolean changeEmail(String login, String email) throws ServiceException {
        boolean result = false;
        if (UserValidator.isValidLogin(login) && UserValidator.isValidEmail(email)) {
            UserDaoImpl dao = UserDaoImpl.getInstance();
            try {
                result = dao.changeEmail(login.strip(), email.strip());
                logger.info("Email for login: " + login + " is changed: " + result);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public boolean changeUsername(String previousLogin, String newLogin) throws ServiceException {
        boolean result = false;
        if (UserValidator.isValidLogin(previousLogin) && UserValidator.isValidLogin(newLogin)) {
            UserDaoImpl dao = UserDaoImpl.getInstance();
            try {
                if (dao.isUniqueLogin(newLogin)) {
                    result = dao.changeUsername(previousLogin, newLogin);
                    logger.info("New login: " + newLogin + " is changed: " + result);
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public int findIdUserByLogin(String login) throws ServiceException {
        int idUser = 0;
        if (UserValidator.isValidLogin(login)) {
            try {
                idUser = UserDaoImpl.getInstance().findIdUserByLogin(login.strip());
                logger.info("User type has been defined successfully");
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        if (idUser <= 0) {
            throw new ServiceException("Id user from database is empty or wrong for login " + login);
        }
        return idUser;
    }

    @Override
    public Map<String, Integer> countUsersQuantityByRole() throws ServiceException {
        Map<String, Integer> result = new HashMap<>();
        UserType[] types = UserType.values();
        UserDao dao = UserDaoImpl.getInstance();
        for (UserType type : types) {
            String keyType = type.name().toLowerCase();
            try {
                int valueType = dao.countUsersByRole(keyType);
                result.put(keyType, valueType);
                logger.info("Count quantity for " + keyType);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

}