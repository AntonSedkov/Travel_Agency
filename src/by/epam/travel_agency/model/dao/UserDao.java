package by.epam.travel_agency.model.dao;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.model.entity.User;

public interface UserDao extends BaseDao<Integer, User> {

    User findByLogin(String login) throws DaoException;

    boolean isUniqueLogin(String login) throws DaoException;

}