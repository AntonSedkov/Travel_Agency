package by.epam.travel_agency.model.service.impl;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.dao.UserDao;
import by.epam.travel_agency.model.dao.impl.UserDaoImpl;
import by.epam.travel_agency.model.entity.User;
import by.epam.travel_agency.model.service.UserService;
import by.epam.travel_agency.util.Encryption;
import by.epam.travel_agency.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


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
                User user = dao.findByLogin(enterLogin);
                if (user.isStatus()) {
                    String encryptedPassword = user.getPassword();
                    result = Encryption.checkPassword(enterPassword, encryptedPassword);
                    logger.info("Checked password");
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public boolean createNewUser(String enterLogin, String enterPass, String enterEmail) throws ServiceException {
        boolean result = false;
        UserDao dao = UserDaoImpl.getInstance();
        if (UserValidator.isValidLogin(enterLogin) && UserValidator.isValidPassword(enterPass) && UserValidator.isValidEmail(enterEmail)) {
            try {
                result = dao.isUniqueLogin(enterLogin);
                logger.info("Checked login for unique value");
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        if (result) {
            User newUser = new User(enterLogin, Encryption.getSaltedHash(enterPass), enterEmail);
            try {
                result = dao.create(newUser);
                logger.info("Create new user");
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

}