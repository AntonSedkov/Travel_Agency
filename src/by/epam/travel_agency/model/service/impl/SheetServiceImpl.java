package by.epam.travel_agency.model.service.impl;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.model.dao.SheetDao;
import by.epam.travel_agency.model.dao.impl.SheetDaoImpl;
import by.epam.travel_agency.model.entity.ClientSheet;
import by.epam.travel_agency.model.service.SheetService;
import by.epam.travel_agency.validator.GeneralValidator;
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
    public ClientSheet findSheetByIdUser(String idUser) throws SecurityException {
        ClientSheet clientSheet = new ClientSheet();
        if (GeneralValidator.isDigitValue(idUser)) {
            SheetDao dao = SheetDaoImpl.getInstance();
            try {
                int idUserInt = Integer.parseInt(idUser);
                clientSheet = dao.findSheetByIdUser(idUserInt);
                logger.info("Find sheet for id user " + idUser);
            } catch (NumberFormatException e) {
                throw new SecurityException("Wrong Id user integer format value");
            } catch (DaoException e) {
                throw new SecurityException(e);
            }
        }
        return clientSheet;
    }

    public Optional<ClientSheet> findOptionalSheetByLogin(String idUser) throws SecurityException {
        SheetDao dao = SheetDaoImpl.getInstance();
        try {
            int idUserInt = Integer.parseInt(idUser);
            Optional<ClientSheet> clientSheet = (GeneralValidator.isDigitValue(idUser))
                    ? Optional.ofNullable(dao.findSheetByIdUser(idUserInt))
                    : Optional.empty();
            logger.info("Find sheet for id user " + idUser);
            return clientSheet;
        } catch (NumberFormatException e) {
            throw new SecurityException("Wrong Id user integer format value");
        } catch (DaoException e) {
            throw new SecurityException(e);
        }
    }

}