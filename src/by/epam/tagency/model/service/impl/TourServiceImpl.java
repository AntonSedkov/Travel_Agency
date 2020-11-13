package by.epam.tagency.model.service.impl;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.dao.TourDao;
import by.epam.tagency.model.dao.impl.TourDaoImpl;
import by.epam.tagency.model.entity.HotelType;
import by.epam.tagency.model.entity.Tour;
import by.epam.tagency.model.entity.TourType;
import by.epam.tagency.model.entity.TransportType;
import by.epam.tagency.model.service.TourService;
import by.epam.tagency.util.DateTimeUtil;
import by.epam.tagency.validator.GeneralValidator;
import by.epam.tagency.validator.TourValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TourServiceImpl implements TourService {
    private static final TourServiceImpl INSTANCE = new TourServiceImpl();
    private static Logger logger = LogManager.getLogger(TourServiceImpl.class);

    private TourServiceImpl() {
    }

    public static TourServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Tour> findAllTours() throws ServiceException {
        List<Tour> tours;
        TourDaoImpl dao = TourDaoImpl.getInstance();
        try {
            tours = dao.findAllTours();
            logger.info("Find all tours.");
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return tours;
    }

    @Override
    public Tour findTourById(String idConcreteTour) throws ServiceException {
        Tour tour = new Tour();
        if (GeneralValidator.isDigitValue(idConcreteTour)) {
            try {
                int idConcreteTourInt = Integer.parseInt(idConcreteTour);
                TourDao dao = TourDaoImpl.getInstance();
                tour = dao.findTourById(idConcreteTourInt);
                logger.info("Find concrete tour " + idConcreteTourInt);
            } catch (NumberFormatException e) {
                throw new ServiceException("Incoming ID is wrong format - not an integer", e);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return tour;
    }

    @Override
    public List<Tour> findToursByParameters(String restType, String country, String startDate, String minDays, String maxPrice) throws ServiceException {
        List<Tour> tours = new ArrayList<>();
        if (TourValidator.isValidTourType(restType) && GeneralValidator.isLatinLiterals(country)
                && GeneralValidator.isDateFormat(startDate) && TourValidator.isDaysValue(minDays)
                && TourValidator.isDigitParamValue(maxPrice)) {
            try {
                TourDao dao = TourDaoImpl.getInstance();
                LocalDate date = LocalDate.parse(startDate.strip());
                long dateSec = DateTimeUtil.convertLongFromLocalDate(date);
                int days = Integer.parseInt(minDays.strip());
                int price = Integer.parseInt(maxPrice.strip());
                tours = dao.findToursByParameters(restType.strip(), country.strip(), dateSec, days, price);
                logger.info("Find tours with parameters.");
            } catch (DateTimeParseException e) {
                throw new ServiceException("Incoming date of birth is wrong format - not date", e);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return tours;
    }

    @Override
    public List<Tour> findToursByCountry(String country) throws ServiceException {
        List<Tour> tours = new ArrayList<>();
        if (GeneralValidator.isLatinLiterals(country)) {
            TourDao dao = TourDaoImpl.getInstance();
            try {
                tours = dao.findToursByCountry(country.strip());
                logger.info("Find tours by country " + country);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return tours;
    }

    @Override
    public List<Tour> findToursByType(String tourType) throws ServiceException {
        List<Tour> tours = new ArrayList<>();
        if (TourValidator.isValidTourType(tourType)) {
            TourDao dao = TourDaoImpl.getInstance();
            try {
                tours = dao.findToursByType(tourType.strip());
                logger.info("Find tours by type " + tourType);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return tours;
    }

    @Override
    public List<Tour> findAllHotTours() throws ServiceException {
        List<Tour> hotTours;
        try {
            TourDao dao = TourDaoImpl.getInstance();
            hotTours = dao.findAllHotTours();
            logger.info("Find hot tours.");
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return hotTours;
    }

    @Override
    public boolean createTour(String type, String country, String hotelName, String stars, String transport,
                              String date, String days, String price, String quantity, String description,
                              String image) throws ServiceException {
        boolean result = false;
        if (TourValidator.isValidTourType(type) && GeneralValidator.isLatinLiterals(country) && GeneralValidator.isLatinLiterals(hotelName)
                && TourValidator.isValidHotelType(stars) && TourValidator.isValidTransportType(transport)
                && GeneralValidator.isDateFormat(date) && TourValidator.isDaysValue(days) && TourValidator.isDigitParamValue(price)
                && TourValidator.isDigitParamValue(quantity) && GeneralValidator.isLatinLiterals(description) && GeneralValidator.isImageName(image)) {
            try {
                Tour tour = new Tour();
                tour.setTourType(TourType.valueOf(type.strip().toUpperCase()));
                tour.setCountry(country.strip());
                tour.setHotelName(hotelName.strip());
                tour.setHotelType(HotelType.valueOf(stars.strip().toUpperCase()));
                tour.setTransport(TransportType.valueOf(transport.strip().toUpperCase()));
                LocalDate localDate = LocalDate.parse(date.strip());
                tour.setStartDate(localDate);
                tour.setDays(Integer.parseInt(days.strip()));
                tour.setPrice(Integer.parseInt(price.strip()));
                tour.setAvailableQuantity(Integer.parseInt(quantity.strip()));
                tour.setDescription(description.strip());
                tour.setImagePath(image.strip());
                TourDao dao = TourDaoImpl.getInstance();
                result = dao.createTour(tour);
                logger.info("Created new tour.");
            } catch (DateTimeParseException e) {
                throw new ServiceException("Incoming date of birth is wrong format - not date", e);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public boolean closeTour(String idTour) throws ServiceException {
        boolean result = false;
        if (GeneralValidator.isDigitValue(idTour)) {
            TourDao dao = TourDaoImpl.getInstance();
            try {
                result = dao.closeTour(Integer.parseInt(idTour));
                logger.info("Close tour - id=" + idTour);
            } catch (NumberFormatException e) {
                throw new ServiceException("Incoming ID is wrong format - not an integer", e);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public boolean setHotTour(String idTour, String discount) throws ServiceException {
        boolean result = false;
        if (GeneralValidator.isDigitValue(idTour) && TourValidator.isDiscountValue(discount)) {
            try {
                int idTourInt = Integer.parseInt(idTour);
                int discountInt = Integer.parseInt(discount);
                TourDao dao = TourDaoImpl.getInstance();
                result = dao.setHotTour(idTourInt, discountInt);
                logger.info("New hot tour - id=" + idTour);
            } catch (NumberFormatException e) {
                throw new ServiceException("Incoming ID is wrong format - not an integer", e);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public Set<String> findAvailableCountries() throws ServiceException {
        Set<String> countries;
        try {
            TourDao dao = TourDaoImpl.getInstance();
            countries = dao.findAvailableCountries();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return countries;
    }

}