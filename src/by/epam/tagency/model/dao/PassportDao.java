package by.epam.tagency.model.dao;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.model.entity.ClientPassport;

import java.util.List;

public interface PassportDao {

    List<ClientPassport> findPassportsByIdUser(int idUser) throws DaoException;

    boolean createPassport(int idUser, ClientPassport passport) throws DaoException;

}