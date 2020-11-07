package by.epam.tagency.model.service;

import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.TravelDocs;

public interface TravelDocsService {

    TravelDocs findTravelDocsById(String idDocs) throws ServiceException;

    boolean addVoucher(String idDocs, String imageName) throws ServiceException;

    boolean addInsurance(String idDocs, String imageName) throws ServiceException;

    boolean addTicket(String idDocs, String imageName) throws ServiceException;

}