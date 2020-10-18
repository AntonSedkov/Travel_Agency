package by.epam.travel_agency.model.dao;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.model.entity.Tour;

import java.util.List;

public interface TourDao extends BaseDao<Integer, Tour> {
    List<Tour> findAll() throws DaoException;
}
