package by.epam.travel_agency.model.dao;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.model.entity.User;

import java.util.List;

public interface UserDao extends BaseDao<Integer, User> {

    boolean findStatusByLogin(String login) throws DaoException;

    String findPassByLogin(String login) throws DaoException;

    String findRoleByLogin(String login) throws DaoException;

    boolean isUniqueLogin(String login) throws DaoException;

    boolean createNewUser(User user, String password) throws DaoException;

    boolean deactivateUser(int id) throws DaoException;

    boolean activateUser(int id) throws DaoException;

    boolean activateUserEmail(String login) throws DaoException;

    boolean changePassword(String login, String password) throws DaoException;

    boolean changeEmail(String login, String email) throws DaoException;

    List<User> findAllUsersWithoutCurrent(String login) throws DaoException;

}