package by.epam.travel_agency.model.dao;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.model.entity.ClientSheet;

public interface SheetDao {

    ClientSheet findSheetByIdUser(int idUser) throws DaoException;

    boolean changeSheetSum(int idUser, int newSum) throws DaoException;

}