package by.epam.travel_agency.model.service.impl;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.dao.PassportDao;
import by.epam.travel_agency.model.dao.impl.PassportDaoImpl;
import by.epam.travel_agency.model.entity.ClientPassport;
import by.epam.travel_agency.model.service.PassportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PassportServiceImpl implements PassportService {
    private static final PassportServiceImpl INSTANCE = new PassportServiceImpl();
    private static Logger logger = LogManager.getLogger(PassportServiceImpl.class);

    private PassportServiceImpl() {
    }

    public static PassportServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<ClientPassport> findPassportsByIdUser(int idUser) throws ServiceException {
        List<ClientPassport> passports;
        try {
            PassportDao dao = PassportDaoImpl.getInstance();
            passports = dao.findPassportsByIdUser(idUser);
            logger.info("Find passports for id user " + idUser);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return passports;
    }

}