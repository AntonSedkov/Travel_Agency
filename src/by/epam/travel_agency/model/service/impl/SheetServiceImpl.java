package by.epam.travel_agency.model.service.impl;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.dao.SheetDao;
import by.epam.travel_agency.model.dao.impl.SheetDaoImpl;
import by.epam.travel_agency.model.entity.ClientSheet;
import by.epam.travel_agency.model.service.SheetService;
import by.epam.travel_agency.validator.GeneralValidator;
import by.epam.travel_agency.validator.PaycardValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SheetServiceImpl implements SheetService {
    private static final SheetServiceImpl INSTANCE = new SheetServiceImpl();
    private static Logger logger = LogManager.getLogger(SheetServiceImpl.class);

    private SheetServiceImpl() {
    }

    public static SheetServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public ClientSheet findSheetByIdUser(int idUser) throws ServiceException {
        ClientSheet clientSheet;
        SheetDao dao = SheetDaoImpl.getInstance();
        try {
            clientSheet = dao.findSheetByIdUser(idUser);
            logger.info("Find sheet for id user " + idUser);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return clientSheet;
    }

    @Override
    public boolean addSheetSum(int idUser, String numberPaycard) throws ServiceException {
        boolean result = false;
        if (PaycardValidator.isPaycardValue(numberPaycard)) {
            SheetDao dao = SheetDaoImpl.getInstance();
            try {
                int numberPaycardInt = Integer.parseInt(numberPaycard);
                result = dao.addSheetSum(idUser, numberPaycardInt);
                logger.info("Add sum for id user " + idUser);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public boolean payOrder(int idUser, String sum) throws ServiceException {
        boolean result = false;
        if (GeneralValidator.isDigitValue(sum)) {                   // TODO: 31.10.2020  order check
            SheetDao dao = SheetDaoImpl.getInstance();
            try {
                int sumInt = Integer.parseInt(sum);
                result = dao.changeSheetSum(idUser, sumInt);        // TODO: 31.10.2020 another method
                logger.info("Pay order for id user " + idUser);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

}