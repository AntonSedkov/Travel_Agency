package by.epam.tagency.model.dao.impl;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.model.connection.ConnectionPool;
import by.epam.tagency.model.dao.ColumnName;
import by.epam.tagency.model.dao.PassportDao;
import by.epam.tagency.model.dao.QuerySql;
import by.epam.tagency.model.entity.ClientPassport;
import by.epam.tagency.util.DateTimeUtil;

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
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.FIND_PASSPORTS_BY_ID_USER)) {
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ClientPassport passport = new ClientPassport();
                passport.setId(resultSet.getInt(ColumnName.ID_PASSPORT));
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

    @Override
    public boolean createPassport(int idUser, ClientPassport passport) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.CREATE_PASSPORT)) {
            preparedStatement.setInt(1, idUser);
            preparedStatement.setString(2, passport.getSurname());
            preparedStatement.setString(3, passport.getName());
            long date = DateTimeUtil.convertLongFromLocalDate(passport.getBirthDate());
            preparedStatement.setLong(4, date);
            preparedStatement.setString(5, passport.getPassportNumber());
            preparedStatement.setString(6, passport.getPassportImage());
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException("Exception of creating passport.", ex);
        }
        return result;
    }

}