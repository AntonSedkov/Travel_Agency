package by.epam.travel_agency.model.service.impl;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.dao.TourDao;
import by.epam.travel_agency.model.dao.impl.TourDaoImpl;
import by.epam.travel_agency.model.entity.HotelType;
import by.epam.travel_agency.model.entity.Tour;
import by.epam.travel_agency.model.entity.TourType;
import by.epam.travel_agency.model.entity.TransportType;
import by.epam.travel_agency.model.service.TourService;
import by.epam.travel_agency.util.DateTimeUtil;
import by.epam.travel_agency.validator.GeneralValidator;
import by.epam.travel_agency.validator.TourValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
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
        List<Tour> result;
        TourDaoImpl dao = TourDaoImpl.getInstance();
        try {
            result = dao.findAllTours();
            logger.info("Find all tours.");
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<Tour> findToursByParameters(String restType, String country, String startDate, String minDays, String maxPrice) throws ServiceException {
        List<Tour> result = new ArrayList<>();
        if (TourValidator.isValidTourType(restType) && GeneralValidator.isLatinLiterals(country)
                && GeneralValidator.isDateFormat(startDate) && TourValidator.isDaysValue(minDays)
                && TourValidator.isDigitParamValue(maxPrice)) {
            TourDao dao = TourDaoImpl.getInstance();
            LocalDate date = LocalDate.parse(startDate.strip());
            long dateSec = DateTimeUtil.countLongFromLocalDate(date);
            int days = Integer.parseInt(minDays.strip());
            int price = Integer.parseInt(maxPrice.strip());
            try {
                result = dao.findToursByParameters(restType.strip(), country.strip(), dateSec, days, price);
                logger.info("Find tours with parameters.");
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public List<Tour> findToursByCountry(String country) throws ServiceException {
        List<Tour> result = new ArrayList<>();
        if (GeneralValidator.isLatinLiterals(country)) {
            TourDao dao = TourDaoImpl.getInstance();
            try {
                result = dao.findToursByCountry(country.strip());
                logger.info("Find tours by country " + country);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public List<Tour> findToursByType(String tourType) throws ServiceException {
        List<Tour> result = new ArrayList<>();
        if (TourValidator.isValidTourType(tourType)) {
            TourDao dao = TourDaoImpl.getInstance();
            try {
                result = dao.findToursByType(tourType.strip());
                logger.info("Find tours by type " + tourType);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public List<Tour> findAllHotTours() throws ServiceException {
        List<Tour> result;
        TourDao dao = TourDaoImpl.getInstance();
        try {
            result = dao.findAllHotTours();
            logger.info("Find hot tours.");
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean createTour(String type, String country, String hotelName, String stars, String transport,
                              String date, String days, String price, String quantity, String description,
                              String image) throws ServiceException {
        boolean result = false;
        if (TourValidator.isValidTourType(type) && GeneralValidator.isLatinLiterals(country) && GeneralValidator.isLatinLiterals(hotelName)
                && TourValidator.isValidHotelType(stars) && TourValidator.isValidTransportType(transport)
                && GeneralValidator.isDateFormat(date) && TourValidator.isDaysValue(days) && TourValidator.isDigitParamValue(price)
                && TourValidator.isDigitParamValue(quantity) && GeneralValidator.isLatinLiterals(description) && TourValidator.isImageName(image)) {
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
            try {
                result = dao.createTour(tour);
                logger.info("Created new tour.");
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
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public Set<String> findAvailableCountries() throws ServiceException {
        TourDao dao = TourDaoImpl.getInstance();
        Set<String> countries;
        try {
            countries = dao.findAvailableCountries();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return countries;
    }

}