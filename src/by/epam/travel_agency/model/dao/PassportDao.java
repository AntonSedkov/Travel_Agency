package by.epam.travel_agency.model.dao;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.model.entity.ClientPassport;

import java.util.List;

public interface PassportDao {

    List<ClientPassport> findPassportsByIdUser(int idUser) throws DaoException;

}