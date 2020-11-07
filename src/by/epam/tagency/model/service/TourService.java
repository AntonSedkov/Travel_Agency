package by.epam.tagency.model.service;

import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.Tour;

import java.util.List;
import java.util.Set;

public interface TourService {

    List<Tour> findAllTours() throws ServiceException;

    Tour findTourById(String idConcreteTour) throws ServiceException;

    List<Tour> findToursByParameters(String restType, String country, String startDate, String minDays, String maxPrice) throws ServiceException;

    List<Tour> findToursByCountry(String country) throws ServiceException;

    List<Tour> findToursByType(String restType) throws ServiceException;

    List<Tour> findAllHotTours() throws ServiceException;

    Set<String> findAvailableCountries() throws ServiceException;

    boolean createTour(String type, String country, String hotelName, String stars,
                       String transport, String date, String days, String price, String quantity,
                       String description, String image) throws ServiceException;

    boolean closeTour(String idTour) throws ServiceException;

    boolean setHotTour(String idTour, String discount) throws ServiceException;

}