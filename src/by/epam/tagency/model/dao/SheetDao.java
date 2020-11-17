package by.epam.tagency.model.dao;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.model.entity.ClientSheet;

/**
 * Interface for {@link ClientSheet} database interactions
 *
 * @author Anton Sedkov
 * @version 1.0
 */
public interface SheetDao extends BaseDao<Integer, ClientSheet> {

    /**
     * Search for the concrete sheet for the concrete user in database
     *
     * @param idUser reference to the identifier of the concrete user
     * @return {@code ClientSheet} for this user
     * @throws DaoException when {@link java.sql.SQLException} has happened
     * @see by.epam.tagency.model.entity.User
     */
    ClientSheet findSheetByIdUser(int idUser) throws DaoException;

    /**
     * Method provides one transaction for adding sum to the user sheet in database:
     * at first check for the valid of paycard number,
     * then adding the paycard sum to the concrete user sheet,
     * in the end, create {@code SheetOperation} for this sheet
     *
     * @param idUser        reference to the identifier of the concrete user
     * @param numberPaycard reference to the number of the paycard
     * @return {@code true} if adding sum transaction is successful; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     * @see by.epam.tagency.model.entity.User
     */
    boolean addSheetSum(int idUser, int numberPaycard) throws DaoException;

}