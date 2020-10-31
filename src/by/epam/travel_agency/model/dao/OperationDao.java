package by.epam.travel_agency.model.dao;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.model.entity.SheetOperation;

import java.util.List;

public interface OperationDao {

    List<SheetOperation> findOperationsByIdSheet(int idSheet) throws DaoException;

}