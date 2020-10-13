package by.epam.travel_agency.model.dao.impl;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.model.connection.ConnectionPool;
import by.epam.travel_agency.model.dao.ColumnName;
import by.epam.travel_agency.model.dao.StatementSql;
import by.epam.travel_agency.model.dao.TourDao;
import by.epam.travel_agency.model.entity.HotelType;
import by.epam.travel_agency.model.entity.Tour;
import by.epam.travel_agency.model.entity.TourType;
import by.epam.travel_agency.model.entity.TransportType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TourDaoImpl implements TourDao {
    private static final TourDaoImpl INSTANCE = new TourDaoImpl();
    private static ConnectionPool pool = ConnectionPool.INSTANCE;

    private TourDaoImpl() {
    }

    public static TourDaoImpl getInstance() {
        return INSTANCE;
    }


    @Override
    public List<Tour> findAll() throws DaoException {
        List<Tour> result = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.FIND_ALL_TOUR_ACTIVE)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tour tour = new Tour();
                tour.setId(resultSet.getInt(ColumnName.ID_TOUR));
                tour.setTourType(TourType.valueOf(resultSet.getString(ColumnName.TOUR_PURPOSE).toUpperCase()));
                tour.setCountry(resultSet.getString(ColumnName.COUNTRY));
                tour.setHotelName(resultSet.getString(ColumnName.HOTEL_NAME));
                tour.setHotelType(HotelType.valueOf(resultSet.getString(ColumnName.HOTEL_STARS).toUpperCase()));
                tour.setTransport(TransportType.valueOf(resultSet.getString(ColumnName.TRANSPORT).toUpperCase()));
                tour.setStartDate(resultSet.getLong(ColumnName.DATE_START));
                tour.setDays(resultSet.getInt(ColumnName.QUANTITY_OF_DAYS));
                tour.setPrice(resultSet.getInt(ColumnName.PRICE));
                tour.setAvailableQuantity(resultSet.getInt(ColumnName.QUANTITY_TOURS));
                tour.setDescription(resultSet.getString(ColumnName.DESCRIPTION));
                tour.setImagePath(resultSet.getString(ColumnName.IMAGE_PATH));
                result.add(tour);
            }
        } catch (SQLException ex) {
            throw new DaoException("Exception of finding all tours.", ex);
        }
        return result;


    }
}
