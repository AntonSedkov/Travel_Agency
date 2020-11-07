package by.epam.tagency.model.service;

import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.SheetOperation;

import java.util.List;

public interface OperationService {

    List<SheetOperation> findOperationsByIdSheet(int idSheet) throws ServiceException;

}