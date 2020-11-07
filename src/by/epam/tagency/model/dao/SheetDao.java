package by.epam.tagency.model.dao;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.model.entity.ClientSheet;

public interface SheetDao {

    ClientSheet findSheetByIdUser(int idUser) throws DaoException;

    boolean addSheetSum(int idUser, int numberPaycard) throws DaoException;

    boolean changeSheetSum(int idUser, int newSum) throws DaoException;

}