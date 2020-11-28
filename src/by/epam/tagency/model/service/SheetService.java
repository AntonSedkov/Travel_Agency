package by.epam.tagency.model.service;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.ClientSheet;

/**
 * Interface for {@link ClientSheet} service actions
 *
 * @author Anton Sedkov
 * @version 1.0
 */
public interface SheetService {

    /**
     * Search for the concrete sheet for the concrete user
     *
     * @param idUser reference to the identifier of the concrete user
     * @return {@code ClientSheet} for this user
     * @throws ServiceException when {@link DaoException} has happened
     * @see by.epam.tagency.model.entity.User
     */
    ClientSheet findSheetByIdUser(int idUser) throws ServiceException;

    /**
     * Result of adding sum to the user sheet
     *
     * @param idUser        reference to the identifier of the concrete user
     * @param numberPaycard reference to the number of the paycard
     * @return {@code true} if parameters is correct and adding sum is successful; {@code false} otherwise
     * @throws ServiceException when {@link DaoException} has happened
     * @see by.epam.tagency.model.entity.User
     */
    boolean addSheetSum(int idUser, String numberPaycard) throws ServiceException;

}