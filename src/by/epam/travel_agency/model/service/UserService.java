package by.epam.travel_agency.model.service;

import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.entity.User;
import by.epam.travel_agency.model.entity.UserType;

import java.util.List;

public interface UserService {
    boolean checkLoginData(String enterLogin, String enterPassword) throws ServiceException;

    boolean createNewUser(String enterLogin, String enterPass, String enterEmail) throws ServiceException;

    UserType findRoleByLogin(String enterLogin) throws ServiceException;

    boolean activateUser(int id) throws ServiceException;

    boolean deactivateUser(int id) throws ServiceException;

    boolean activateUserEmail(String login) throws ServiceException;

    List<User> findAllUsers() throws ServiceException;

    boolean changePassword(String login, String password) throws ServiceException;

    boolean changeEmail(String login, String email) throws ServiceException;

}