package by.epam.tagency.model.service;

import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.ClientSheet;

public interface SheetService {

    ClientSheet findSheetByIdUser(int idUser) throws ServiceException;

    boolean addSheetSum(int idUser, String numberPaycard) throws ServiceException;

    boolean payOrder(int idUser, String sum) throws ServiceException;

}