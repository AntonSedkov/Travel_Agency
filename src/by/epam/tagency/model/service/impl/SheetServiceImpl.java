package by.epam.tagency.model.service.impl;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.dao.SheetDao;
import by.epam.tagency.model.dao.impl.SheetDaoImpl;
import by.epam.tagency.model.entity.ClientSheet;
import by.epam.tagency.model.service.SheetService;
import by.epam.tagency.validator.PaycardValidator;
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

}