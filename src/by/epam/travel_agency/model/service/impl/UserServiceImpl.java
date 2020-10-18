package by.epam.travel_agency.model.service.impl;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.dao.UserDao;
import by.epam.travel_agency.model.dao.impl.UserDaoImpl;
import by.epam.travel_agency.model.entity.User;
import by.epam.travel_agency.model.entity.UserType;
import by.epam.travel_agency.model.service.UserService;
import by.epam.travel_agency.util.EncryptionManager;
import by.epam.travel_agency.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


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
                boolean userStatus = dao.findStatusByLogin(enterLogin);
                if (userStatus) {
                    String encryptedPassword = dao.findPassByLogin(enterLogin);
                    if (encryptedPassword == null || encryptedPassword.isBlank()) {
                        throw new ServiceException("Password from database is Empty for login " + enterLogin);
                    }
                    result = EncryptionManager.checkPassword(enterPassword, encryptedPassword);
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
            result = checkUniqueLogin(enterLogin);
            if (result) {
                User newUser = new User(enterLogin, enterEmail, enterRole);
                String encryptedPassword = EncryptionManager.getSaltedHash(enterPass);
                try {
                    result = dao.createNewUser(newUser, encryptedPassword);
                    logger.info("Create new user - client");
                } catch (DaoException e) {
                    throw new ServiceException(e);
                }
            }
        }
        return result;
    }

    @Override
    public UserType findRoleByLogin(String enterLogin) throws ServiceException {
        try {
            String strRole = UserDaoImpl.getInstance().findRoleByLogin(enterLogin);
            if (strRole == null || strRole.isBlank()) {
                throw new ServiceException("Role from database is Empty for login " + enterLogin);
            }
            UserType role = UserType.valueOf(strRole.toUpperCase());
            logger.info("User type has been defined successfully");
            return role;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean activateUser(String id) throws ServiceException {
        boolean result;
        try {
            int idInt = Integer.parseInt(id);
            UserDaoImpl dao = UserDaoImpl.getInstance();
            result = dao.activateUser(idInt);
            logger.info("User with id: " + id + " has been activated.");
        } catch (NumberFormatException e) {
            throw new ServiceException("Incoming ID is wrong format - not an integer", e);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean deactivateUser(String id) throws ServiceException {
        boolean result;
        try {
            int idInt = Integer.parseInt(id);
            UserDaoImpl dao = UserDaoImpl.getInstance();
            result = dao.deactivateUser(idInt);
            logger.info("User with id: " + id + " has been deactivated.");
        } catch (NumberFormatException e) {
            throw new ServiceException("Incoming ID is wrong format - not an integer", e);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean activateUserEmail(String login) throws ServiceException {
        boolean result;
        UserDaoImpl dao = UserDaoImpl.getInstance();
        try {
            result = dao.activateUserEmail(login);
            logger.info("Email: " + login + " has been activated.");
        } catch (DaoException e) {
            throw new ServiceException(e);
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
    public boolean changePassword(String login, String password) throws ServiceException {
        boolean result;
        UserDaoImpl dao = UserDaoImpl.getInstance();
        try {
            result = dao.changePassword(login, password);
            logger.info("Password for login: " + login + " is " + result);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean changeEmail(String login, String email) throws ServiceException {
        boolean result;
        UserDaoImpl dao = UserDaoImpl.getInstance();
        try {
            result = dao.changeEmail(login, email);
            logger.info("Email for login: " + login + " is " + result);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    private boolean checkUniqueLogin(String login) throws ServiceException {
        boolean result;
        try {
            result = UserDaoImpl.getInstance().isUniqueLogin(login);
            logger.info("Checked login for unique value successfully");
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

}