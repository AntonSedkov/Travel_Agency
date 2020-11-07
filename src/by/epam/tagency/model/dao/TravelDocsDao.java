package by.epam.tagency.model.dao;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.model.entity.TravelDocs;

public interface TravelDocsDao {

    TravelDocs findTravelDocsById(int idDocs) throws DaoException;

    boolean addVoucher(int idDocs, String imageName) throws DaoException;

    boolean addInsurance(int idDocs, String imageName) throws DaoException;

    boolean addTicket(int idDocs, String imageName) throws DaoException;

}