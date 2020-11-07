package by.epam.tagency.model.dao;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.model.entity.SheetOperation;

import java.util.List;

public interface OperationDao {

    List<SheetOperation> findOperationsByIdSheet(int idSheet) throws DaoException;

}