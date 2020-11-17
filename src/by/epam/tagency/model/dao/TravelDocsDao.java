package by.epam.tagency.model.dao;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.model.entity.TravelDocs;

/**
 * Interface for {@link TravelDocs} database interactions
 *
 * @author Anton Sedkov
 * @version 1.0
 */
public interface TravelDocsDao extends BaseDao<Integer, TravelDocs> {

    /**
     * Search for the concrete travel docs in database
     *
     * @param idDocs reference to the identifier of the concrete docs
     * @return concrete {@code TravelDocs}
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    TravelDocs findTravelDocsById(int idDocs) throws DaoException;

    /**
     * Add the voucher to the concrete travel docs in database
     *
     * @param idDocs    reference to the identifier of the concrete docs
     * @param imageName defines the name of voucher which is in the folder for docs
     * @return {@code true} if voucher has been added successfully; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    boolean addVoucher(int idDocs, String imageName) throws DaoException;

    /**
     * Add the insurance to the concrete travel docs in database
     *
     * @param idDocs    reference to the identifier of the concrete docs
     * @param imageName defines the name of insurance which is in the folder for docs
     * @return {@code true} if insurance has been added successfully; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    boolean addInsurance(int idDocs, String imageName) throws DaoException;

    /**
     * Add the ticket to the concrete travel docs in database
     *
     * @param idDocs    reference to the identifier of the concrete docs
     * @param imageName defines the name of ticket which is in the folder for docs
     * @return {@code true} if ticket has been added successfully; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    boolean addTicket(int idDocs, String imageName) throws DaoException;

}