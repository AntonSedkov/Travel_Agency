package by.epam.tagency.model.service;

import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.User;
import by.epam.tagency.model.entity.UserType;

import java.util.List;
import java.util.Map;

public interface UserService {

    boolean checkLoginData(String enterLogin, String enterPassword) throws ServiceException;

    boolean createNewUser(String enterLogin, String enterPass, String enterEmail, String enterRole) throws ServiceException;

    UserType findRoleByUsername(String enterLogin) throws ServiceException;

    boolean activateUser(String id) throws ServiceException;

    boolean deactivateUser(String id) throws ServiceException;

    boolean activateUserEmail(String login) throws ServiceException;

    List<User> findAllUsersWithoutCurrent(String login) throws ServiceException;

    Map<String, Integer> countUsersQuantityByRole() throws ServiceException;

    boolean changePassword(String login, String previousPassword, String newPassword) throws ServiceException;

    boolean changeEmail(String login, String email) throws ServiceException;

    boolean changeUsername(String previousLogin, String newLogin) throws ServiceException;

    int findIdUserByLogin(String login) throws ServiceException;

}