package by.epam.travel_agency.model.service.impl;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.model.dao.SheetDao;
import by.epam.travel_agency.model.dao.impl.SheetDaoImpl;
import by.epam.travel_agency.model.entity.ClientSheet;
import by.epam.travel_agency.model.service.SheetService;
import by.epam.travel_agency.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class SheetServiceImpl implements SheetService {
    private static final SheetServiceImpl INSTANCE = new SheetServiceImpl();
    private static Logger logger = LogManager.getLogger(SheetServiceImpl.class);

    private SheetServiceImpl() {
    }

    public static SheetServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public ClientSheet findSheetByUsername(String login) throws SecurityException {
        ClientSheet clientSheet = new ClientSheet();
        if (UserValidator.isValidLogin(login)) {
            SheetDao dao = SheetDaoImpl.getInstance();
            try {
                clientSheet = dao.findSheetByIdUser(login);
                logger.info("Find sheet for username " + login);
            } catch (DaoException e) {
                throw new SecurityException(e);
            }
        }
        return clientSheet;
    }


    public Optional<ClientSheet> findOptionalSheetByLogin(String login) throws SecurityException {
        SheetDao dao = SheetDaoImpl.getInstance();
        try {
            Optional<ClientSheet> clientSheet = (UserValidator.isValidLogin(login))
                    ? Optional.ofNullable(dao.findSheetByIdUser(login))
                    : Optional.empty();
            logger.info("Find sheet for username " + login);
            return clientSheet;
        } catch (DaoException e) {
            throw new SecurityException(e);
        }
    }

    @Override
    public ClientSheet findSheetByIdSheet(String login) throws SecurityException {
        return null;
    }

}