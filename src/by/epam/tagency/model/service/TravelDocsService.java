package by.epam.tagency.model.service;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.TravelDocs;

/**
 * Interface for {@link TravelDocs}  service actions
 *
 * @author Anton Sedkov
 * @version 1.0
 */
public interface TravelDocsService {

    /**
     * Search for the concrete travel docs
     *
     * @param idDocs reference to the identifier of the concrete docs
     * @return concrete {@code TravelDocs}
     * @throws ServiceException when {@link DaoException} has happened
     */
    TravelDocs findTravelDocsById(String idDocs) throws ServiceException;

    /**
     * Result of adding the voucher to the concrete travel docs
     *
     * @param idDocs    reference to the identifier of the concrete docs
     * @param imageName defines the name of voucher which is in the folder for docs
     * @return {@code true} if voucher has been added successfully; {@code false} otherwise
     * @throws ServiceException when {@link DaoException} has happened or wrong format of incoming parameter
     */
    boolean addVoucher(String idDocs, String imageName) throws ServiceException;

    /**
     * Result of adding the insurance to the concrete travel docs
     *
     * @param idDocs    reference to the identifier of the concrete docs
     * @param imageName defines the name of insurance which is in the folder for docs
     * @return {@code true} if insurance has been added successfully; {@code false} otherwise
     * @throws ServiceException when {@link DaoException} has happened or wrong format of incoming parameter
     */
    boolean addInsurance(String idDocs, String imageName) throws ServiceException;

    /**
     * Result of adding the ticket to the concrete travel docs
     *
     * @param idDocs    reference to the identifier of the concrete docs
     * @param imageName defines the name of ticket which is in the folder for docs
     * @return {@code true} if ticket has been added successfully; {@code false} otherwise
     * @throws ServiceException when {@link DaoException} has happened or wrong format of incoming parameter
     */
    boolean addTicket(String idDocs, String imageName) throws ServiceException;

}