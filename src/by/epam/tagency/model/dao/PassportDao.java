package by.epam.tagency.model.dao;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.model.entity.ClientPassport;

import java.util.List;

/**
 * Interface for {@link ClientPassport} database interactions
 *
 * @author Anton Sedkov
 * @version 1.0
 */
public interface PassportDao extends BaseDao<Integer, ClientPassport> {

    /**
     * Search for all passports for the concrete user in database
     *
     * @param idUser reference to the identifier of the concrete user
     * @return {@link List} of passports
     * @throws DaoException DaoException when {@link java.sql.SQLException} has happened
     * @see by.epam.tagency.model.entity.User
     */
    List<ClientPassport> findPassportsByIdUser(int idUser) throws DaoException;

    /**
     * Create the passport for the concrete user in database
     *
     * @param idUser   reference to the identifier of the concrete user
     * @param passport {@code ClientPassport} for insertion into database
     * @return {@code true} if passport creation is successful; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     * @see by.epam.tagency.model.entity.User
     */
    boolean createPassport(int idUser, ClientPassport passport) throws DaoException;

}