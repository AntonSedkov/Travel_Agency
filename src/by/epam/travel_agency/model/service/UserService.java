package by.epam.travel_agency.model.service;

import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.entity.UserType;

public interface UserService {
    boolean checkLoginData(String enterLogin, String enterPassword) throws ServiceException;

    boolean createNewUser(String enterLogin, String enterPass, String enterEmail) throws ServiceException;

    UserType findRoleByLogin(String enterLogin) throws ServiceException;
}