package by.epam.tagency.model.dao;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.model.entity.Tour;

import java.util.List;
import java.util.Set;

public interface TourDao extends BaseDao<Integer, Tour> {

    List<Tour> findAllTours() throws DaoException;

    Tour findTourById(int idConcreteTour) throws DaoException;

    List<Tour> findToursByParameters(String restType, String country, long startDate, int minDays, int maxPrice) throws DaoException;

    List<Tour> findToursByCountry(String country) throws DaoException;

    List<Tour> findToursByType(String restType) throws DaoException;

    List<Tour> findAllHotTours() throws DaoException;

    Set<String> findAvailableCountries() throws DaoException;

    boolean createTour(Tour tour) throws DaoException;

    boolean closeTour(int idTour) throws DaoException;

    boolean setHotTour(int idTour, int discount) throws DaoException;

}