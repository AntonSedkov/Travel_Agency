package by.epam.travel_agency.model.service;

import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.entity.ClientSheet;

public interface SheetService {

    ClientSheet findSheetByIdUser(String idUser) throws ServiceException;

}