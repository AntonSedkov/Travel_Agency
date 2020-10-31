package by.epam.travel_agency.model.dao.impl;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.model.connection.ConnectionPool;
import by.epam.travel_agency.model.dao.ColumnName;
import by.epam.travel_agency.model.dao.PassportDao;
import by.epam.travel_agency.model.dao.StatementSql;
import by.epam.travel_agency.model.entity.ClientPassport;
import by.epam.travel_agency.util.DateTimeUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PassportDaoImpl implements PassportDao {
    private static final PassportDaoImpl INSTANCE = new PassportDaoImpl();
    private static ConnectionPool pool = ConnectionPool.INSTANCE;

    private PassportDaoImpl() {
    }

    public static PassportDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<ClientPassport> findPassportsByIdUser(int idUser) throws DaoException {
        List<ClientPassport> passports = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.FIND_PASSPORTS_BY_ID_USER)) {
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ClientPassport passport = new ClientPassport();
                passport.setSurname(resultSet.getString(ColumnName.SURNAME));
                passport.setName(resultSet.getString(ColumnName.NAME));
                LocalDate localDate = DateTimeUtil.convertLocalDateFromLong(resultSet.getLong(ColumnName.BIRTH_DATE));
                passport.setBirthDate(localDate);
                passport.setPassportNumber(resultSet.getString(ColumnName.PASSPORT_NUMBER));
                passport.setPassportImage(resultSet.getString(ColumnName.PASSPORT_IMAGE));
                passports.add(passport);
            }
        } catch (SQLException ex) {
            throw new DaoException("Exception of finding passports by id user.", ex);
        }
        return passports;
    }

}