package by.epam.travel_agency.model.service;

import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.entity.ClientSheet;

public interface SheetService {

    ClientSheet findSheetByIdUser(int idUser) throws ServiceException;

    boolean addSheetSum(int idUser, String numberPaycard) throws ServiceException;

    boolean payOrder(int idUser, String sum) throws ServiceException;

}