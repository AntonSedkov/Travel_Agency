package by.epam.travel_agency.model.dao;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.model.entity.Tour;

import java.util.List;
import java.util.Set;

public interface TourDao extends BaseDao<Integer, Tour> {

    List<Tour> findAllTours() throws DaoException;

    List<Tour> findToursByParameters(String restType, String country, long startDate, int minDays, int maxPrice) throws DaoException;

    List<Tour> findToursByCountry(String country) throws DaoException;

    List<Tour> findToursByType(String restType) throws DaoException;

    List<Tour> findAllHotTours() throws DaoException;

    Set<String> findAvailableCountries() throws DaoException;

    boolean createTour(Tour tour) throws DaoException;

}