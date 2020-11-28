package by.epam.tagency.model.service;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.Tour;

import java.util.List;
import java.util.Set;

/**
 * Interface for {@link Tour} service actions
 *
 * @author Anton Sedkov
 * @version 1.0
 */
public interface TourService {

    /**
     * Search for all tours where quantity bigger than 0
     *
     * @return {@link List} of tours
     * @throws ServiceException when {@link DaoException} has happened
     */
    List<Tour> findAllTours() throws ServiceException;

    /**
     * Validate incoming parameter and search for the concrete tour
     *
     * @param idConcreteTour reference to the identifier of the concrete tour
     * @return concrete {@link Tour}
     * @throws ServiceException when {@link DaoException} has happened or wrong format of incoming parameter
     */
    Tour findTourById(String idConcreteTour) throws ServiceException;

    /**
     * Validate incoming parameters and search for tours matching parameters
     *
     * @param restType  is specified {@code TourType}
     * @param country   is specified country
     * @param startDate is specified date
     * @param minDays   is specified quantity of days
     * @param maxPrice  is specified price
     * @return {@link List} of tours
     * @throws ServiceException when {@link DaoException} has happened or wrong format of incoming parameter
     * @see by.epam.tagency.model.entity.TourType
     **/
    List<Tour> findToursByParameters(String restType, String country, String startDate, String minDays, String maxPrice) throws ServiceException;

    /**
     * Validate incoming parameter and search for tours corresponding with the country
     *
     * @param country is specified country
     * @return {@link List} of tours
     * @throws ServiceException when {@link DaoException} has happened
     */
    List<Tour> findToursByCountry(String country) throws ServiceException;

    /**
     * Validate incoming parameter and search for tours corresponding with the rest type
     *
     * @param restType is specified {@code TourType}
     * @return {@link List} of tours
     * @throws ServiceException when {@link DaoException} has happened
     * @see by.epam.tagency.model.entity.TourType
     */
    List<Tour> findToursByType(String restType) throws ServiceException;

    /**
     * Search for all tours having discount bigger 0 and quantity bigger than 0
     *
     * @return {@link List} of tours
     * @throws ServiceException when {@link DaoException} has happened
     */
    List<Tour> findAllHotTours() throws ServiceException;

    /**
     * Search for all available countries
     *
     * @return {@link Set} of available countries
     * @throws ServiceException when {@link DaoException} has happened
     */
    Set<String> findAvailableCountries() throws ServiceException;

    /**
     * Result of validation incoming parameters and creation tour with specified parameters
     *
     * @param type        is specified {@code TourType} for tour
     * @param country     is specified country for tour
     * @param hotelName   is specified name of hotel for tour
     * @param stars       is specified {@code HotelType} for tour
     * @param transport   is specified {@code TransportType} for tour
     * @param date        is specified data for tour
     * @param days        is specified quantity of days for tour
     * @param price       is specified price for tour
     * @param quantity    is specified quantity of tours for tour
     * @param description is specified description for tour
     * @param image       is specified image name for tour
     * @return {@code true} if tour creation is successful; {@code false} otherwise
     * @throws ServiceException when {@link DaoException} has happened or wrong format of incoming parameter
     * @see by.epam.tagency.model.entity.TourType
     * @see by.epam.tagency.model.entity.HotelType
     * @see by.epam.tagency.model.entity.TransportType
     */
    boolean createTour(String type, String country, String hotelName, String stars,
                       String transport, String date, String days, String price, String quantity,
                       String description, String image) throws ServiceException;

    /**
     * Result of validation incoming parameter and closing the concrete tour
     *
     * @param idTour reference to the identifier of the concrete tour
     * @return {@code true} if tour closing is successful; {@code false} otherwise
     * @throws ServiceException when {@link DaoException} has happened or wrong format of incoming parameter
     */
    boolean closeTour(String idTour) throws ServiceException;

    /**
     * Result of validation incoming parameter and setting specified discount to the concrete tour
     *
     * @param idTour   reference to the identifier of the concrete tour
     * @param discount is specified discount
     * @return {@code true} if tour discounting is successful; {@code false} otherwise
     * @throws ServiceException when {@link DaoException} has happened or wrong format of incoming parameter
     */
    boolean setHotTour(String idTour, String discount) throws ServiceException;

}