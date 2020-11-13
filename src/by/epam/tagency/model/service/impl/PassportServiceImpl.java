package by.epam.tagency.model.service.impl;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.dao.PassportDao;
import by.epam.tagency.model.dao.impl.PassportDaoImpl;
import by.epam.tagency.model.entity.ClientPassport;
import by.epam.tagency.model.service.PassportService;
import by.epam.tagency.validator.GeneralValidator;
import by.epam.tagency.validator.PassportValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

    @Override
    public boolean createPassport(int idUser, String surname, String name, String birthDate, String passportNo, String imageName) throws ServiceException {
        boolean result = false;
        if (PassportValidator.isPersonalString(surname) && PassportValidator.isPersonalString(name) && GeneralValidator.isDateFormat(birthDate)
                && PassportValidator.isPassportNumber(passportNo) && GeneralValidator.isImageName(imageName)) {
            try {
                ClientPassport passport = new ClientPassport();
                passport.setSurname(surname.strip());
                passport.setName(name.strip());
                LocalDate birthLocalDate = LocalDate.parse(birthDate);
                passport.setBirthDate(birthLocalDate);
                passport.setPassportNumber(passportNo.strip());
                passport.setPassportImage(imageName.strip());
                PassportDao dao = PassportDaoImpl.getInstance();
                result = dao.createPassport(idUser, passport);
                logger.info("Create passport for user " + idUser);
            } catch (DateTimeParseException e) {
                throw new ServiceException("Incoming date of birth is wrong format - not date", e);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

}