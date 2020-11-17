package by.epam.tagency.model.dao;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.model.entity.SheetOperation;

import java.util.List;

/**
 * Interface for {@link SheetOperation} database interactions
 *
 * @author Anton Sedkov
 * @version 1.0
 */
public interface OperationDao extends BaseDao<Integer, SheetOperation> {

    /**
     * Select the operations for the concrete sheet from database
     *
     * @param idSheet reference to the identifier of the concrete sheet
     * @return {@link List} of operations
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    List<SheetOperation> findOperationsByIdSheet(int idSheet) throws DaoException;

}