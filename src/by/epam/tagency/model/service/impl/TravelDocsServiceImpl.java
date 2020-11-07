package by.epam.tagency.model.service.impl;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.dao.TravelDocsDao;
import by.epam.tagency.model.dao.impl.TravelDocsDaoImpl;
import by.epam.tagency.model.entity.TravelDocs;
import by.epam.tagency.model.service.TravelDocsService;
import by.epam.tagency.validator.GeneralValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TravelDocsServiceImpl implements TravelDocsService {
    private static final TravelDocsServiceImpl INSTANCE = new TravelDocsServiceImpl();
    private static Logger logger = LogManager.getLogger(TravelDocsServiceImpl.class);

    private TravelDocsServiceImpl() {
    }

    public static TravelDocsServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public TravelDocs findTravelDocsById(String idDocs) throws ServiceException {
        TravelDocs travelDocs = new TravelDocs();
        if (GeneralValidator.isDigitValue(idDocs)) {
            int idDocsInt = Integer.parseInt(idDocs);
            TravelDocsDao dao = TravelDocsDaoImpl.getInstance();
            try {
                travelDocs = dao.findTravelDocsById(idDocsInt);
                logger.info("Find concrete tour docs.");
            } catch (NumberFormatException e) {
                throw new ServiceException("Incoming ID is wrong format - not an integer", e);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return travelDocs;
    }

    @Override
    public boolean addVoucher(String idDocs, String imageName) throws ServiceException {
        boolean result = false;
        if (GeneralValidator.isDigitValue(idDocs) && GeneralValidator.isImageName(imageName)) {
            int idDocsInt = Integer.parseInt(idDocs);
            TravelDocsDao dao = TravelDocsDaoImpl.getInstance();
            try {
                result = dao.addVoucher(idDocsInt, imageName);
                logger.info("Added voucher.");
            } catch (NumberFormatException e) {
                throw new ServiceException("Incoming ID is wrong format - not an integer", e);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public boolean addInsurance(String idDocs, String imageName) throws ServiceException {
        boolean result = false;
        if (GeneralValidator.isDigitValue(idDocs) && GeneralValidator.isImageName(imageName)) {
            int idDocsInt = Integer.parseInt(idDocs);
            TravelDocsDao dao = TravelDocsDaoImpl.getInstance();
            try {
                result = dao.addInsurance(idDocsInt, imageName);
                logger.info("Added insurance.");
            } catch (NumberFormatException e) {
                throw new ServiceException("Incoming ID is wrong format - not an integer", e);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public boolean addTicket(String idDocs, String imageName) throws ServiceException {
        boolean result = false;
        if (GeneralValidator.isDigitValue(idDocs) && GeneralValidator.isImageName(imageName)) {
            int idDocsInt = Integer.parseInt(idDocs);
            TravelDocsDao dao = TravelDocsDaoImpl.getInstance();
            try {
                result = dao.addTicket(idDocsInt, imageName);
                logger.info("Added ticket.");
            } catch (NumberFormatException e) {
                throw new ServiceException("Incoming ID is wrong format - not an integer", e);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

}