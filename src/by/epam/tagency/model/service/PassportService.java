package by.epam.tagency.model.service;

import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.ClientPassport;

import java.util.List;

public interface PassportService {

    List<ClientPassport> findPassportsByIdUser(int idUser) throws ServiceException;

    boolean createPassport(int idUser, String surname, String name, String birthDate, String passportNo, String imageName) throws ServiceException;

}