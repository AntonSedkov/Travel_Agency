package by.epam.travel_agency.model.service;

import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.entity.ClientPassport;

import java.util.List;

public interface PassportService {

    List<ClientPassport> findPassportsByIdUser(int idUser) throws ServiceException;

}