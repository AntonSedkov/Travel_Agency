package by.epam.travel_agency.model.service.impl;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.dao.OperationDao;
import by.epam.travel_agency.model.dao.impl.OperationDaoImpl;
import by.epam.travel_agency.model.entity.SheetOperation;
import by.epam.travel_agency.model.service.OperationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OperationServiceImpl implements OperationService {
    private static final OperationServiceImpl INSTANCE = new OperationServiceImpl();
    private static Logger logger = LogManager.getLogger(OperationServiceImpl.class);

    private OperationServiceImpl() {
    }

    public static OperationServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<SheetOperation> findOperationsByIdSheet(int idSheet) throws ServiceException {
        List<SheetOperation> operations;
        OperationDao dao = OperationDaoImpl.getInstance();
        try {
            operations = dao.findOperationsByIdSheet(idSheet);
            logger.info("Finf operations for sheet " + idSheet);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return operations;
    }

}