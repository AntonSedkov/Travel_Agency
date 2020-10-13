package by.epam.travel_agency.model.service;

import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.entity.Tour;

import java.util.List;

public interface TourService {
    List<Tour> findAll() throws ServiceException;
}
