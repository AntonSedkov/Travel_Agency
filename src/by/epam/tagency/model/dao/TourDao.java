package by.epam.tagency.model.dao;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.model.entity.Tour;

import java.util.List;
import java.util.Set;

/**
 * Interface for {@link Tour} database interactions
 *
 * @author Anton Sedkov
 * @version 1.0
 */
public interface TourDao extends BaseDao<Integer, Tour> {

    /**
     * Search for all tours where quantity bigger than 0 in database
     *
     * @return {@link List} of tours
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    List<Tour> findAllTours() throws DaoException;

    /**
     * Search for the concrete tour in database
     *
     * @param idConcreteTour reference to the identifier of the concrete tour
     * @return concrete {@link Tour}
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    Tour findTourById(int idConcreteTour) throws DaoException;

    /**
     * Search for tours matching parameters in database
     *
     * @param restType  is specified {@code TourType}
     * @param country   is specified country
     * @param startDate is specified date
     * @param minDays   is specified quantity of days
     * @param maxPrice  is specified price
     * @return {@link List} of tours
     * @throws DaoException when {@link java.sql.SQLException} has happened
     * @see by.epam.tagency.model.entity.TourType
     **/
    List<Tour> findToursByParameters(String restType, String country, long startDate, int minDays, int maxPrice) throws DaoException;

    /**
     * Search for tours corresponding with the country in database
     *
     * @param country is specified country
     * @return {@link List} of tours
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    List<Tour> findToursByCountry(String country) throws DaoException;

    /**
     * Search for tours corresponding with the rest type in database
     *
     * @param restType is specified {@code TourType}
     * @return {@link List} of tours
     * @throws DaoException when {@link java.sql.SQLException} has happened
     * @see by.epam.tagency.model.entity.TourType
     */
    List<Tour> findToursByType(String restType) throws DaoException;

    /**
     * Search for all tours having discount bigger 0 and quantity bigger than 0 in database
     *
     * @return {@link List} of tours
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    List<Tour> findAllHotTours() throws DaoException;

    /**
     * Search for all available countries in database
     *
     * @return {@link Set} of available countries
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    Set<String> findAvailableCountries() throws DaoException;

    /**
     * Create tour in database
     *
     * @param tour {@code Tour} to create
     * @return {@code true} if tour creation is successful; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    boolean createTour(Tour tour) throws DaoException;

    /**
     * Set 0-quantity to the concrete tour in database
     *
     * @param idTour reference to the identifier of the concrete tour
     * @return {@code true} if tour closing is successful; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    boolean closeTour(int idTour) throws DaoException;

    /**
     * Set specified discount to the concrete tour
     *
     * @param idTour   reference to the identifier of the concrete tour
     * @param discount is specified discount
     * @return {@code true} if tour discounting is successful; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    boolean setHotTour(int idTour, int discount) throws DaoException;

}