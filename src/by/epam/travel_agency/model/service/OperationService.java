package by.epam.travel_agency.model.service;

import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.entity.SheetOperation;

import java.util.List;

public interface OperationService {

    List<SheetOperation> findOperationsByIdSheet(int idSheet) throws ServiceException;

}