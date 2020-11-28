package by.epam.tagency.model.service;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.SheetOperation;

import java.util.List;

/**
 * Interface for {@link SheetOperation} service actions
 *
 * @author Anton Sedkov
 * @version 1.0
 */
public interface OperationService {

    /**
     * Select the operations for the concrete sheet
     *
     * @param idSheet reference to the identifier of the concrete sheet
     * @return {@link List} of operations
     * @throws ServiceException when {@link DaoException} has happened
     */
    List<SheetOperation> findOperationsByIdSheet(int idSheet) throws ServiceException;

}