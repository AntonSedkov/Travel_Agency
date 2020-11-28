package by.epam.tagency.model.service;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.ClientPassport;

import java.util.List;

/**
 * Interface for {@link ClientPassport} service actions
 *
 * @author Anton Sedkov
 * @version 1.0
 */
public interface PassportService {

    /**
     * Search for all passports for the concrete user
     *
     * @param idUser reference to the identifier of the concrete user
     * @return {@link List} of passports
     * @throws ServiceException when {@link DaoException} has happened
     * @see by.epam.tagency.model.entity.User
     */
    List<ClientPassport> findPassportsByIdUser(int idUser) throws ServiceException;

    /**
     * Result of creation the passport for the concrete user
     *
     * @param idUser     reference to the identifier of the concrete user
     * @param surname    is surname for passport
     * @param name       is name for passport
     * @param birthDate  is date of birth for passport
     * @param passportNo is passport number for passport
     * @param imageName  is reference to scan of passport for passport
     * @return {@code true} if all parameters is correct and passport creation is successful; {@code false} otherwise
     * @throws ServiceException when {@link DaoException} has happened or wrong format of incoming parameter
     * @see by.epam.tagency.model.entity.User
     */
    boolean createPassport(int idUser, String surname, String name, String birthDate, String passportNo, String imageName) throws ServiceException;

}