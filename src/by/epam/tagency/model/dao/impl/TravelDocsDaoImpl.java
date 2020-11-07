package by.epam.tagency.model.dao.impl;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.model.connection.ConnectionPool;
import by.epam.tagency.model.dao.ColumnName;
import by.epam.tagency.model.dao.QuerySql;
import by.epam.tagency.model.dao.TravelDocsDao;
import by.epam.tagency.model.entity.TravelDocs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TravelDocsDaoImpl implements TravelDocsDao {
    private static final TravelDocsDaoImpl INSTANCE = new TravelDocsDaoImpl();
    private static ConnectionPool pool = ConnectionPool.INSTANCE;

    private TravelDocsDaoImpl() {
    }

    public static TravelDocsDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public TravelDocs findTravelDocsById(int idDocs) throws DaoException {
        TravelDocs travelDocs = new TravelDocs();
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.FIND_TRAVEL_DOCS_BY_ID)) {
            preparedStatement.setInt(1, idDocs);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                travelDocs.setVoucher(resultSet.getString(ColumnName.VOUCHER));
                travelDocs.setInsurance(resultSet.getString(ColumnName.INSURANCE));
                travelDocs.setTicket(resultSet.getString(ColumnName.TICKET));
            }
        } catch (SQLException ex) {
            throw new DaoException("Exception of finding travel docs.", ex);
        }
        return travelDocs;
    }

    @Override
    public boolean addVoucher(int idDocs, String imageName) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.ADD_VOUCHER_TO_TRAVEL_DOCS)) {
            preparedStatement.setString(1, imageName);
            preparedStatement.setInt(2, idDocs);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException("Exception of adding voucher.", ex);
        }
        return result;
    }

    @Override
    public boolean addInsurance(int idDocs, String imageName) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.ADD_INSURANCE_TO_TRAVEL_DOCS)) {
            preparedStatement.setString(1, imageName);
            preparedStatement.setInt(2, idDocs);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException("Exception of adding insurance.", ex);
        }
        return result;
    }

    @Override
    public boolean addTicket(int idDocs, String imageName) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.ADD_TICKET_TO_TRAVEL_DOCS)) {
            preparedStatement.setString(1, imageName);
            preparedStatement.setInt(2, idDocs);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException("Exception of adding ticket.", ex);
        }
        return result;
    }

}