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
import by.epam.travel_agency.util.DateTimeUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TourDaoImpl implements TourDao {
    private static final TourDaoImpl INSTANCE = new TourDaoImpl();
    private static ConnectionPool pool = ConnectionPool.INSTANCE;

    private TourDaoImpl() {
    }

    public static TourDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Tour> findAllTours() throws DaoException {
        List<Tour> result = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.FIND_ALL_TOURS_ACTIVE)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tour tour = createTourFromResultSet(resultSet);
                result.add(tour);
            }
        } catch (SQLException ex) {
            throw new DaoException("Exception of finding all tours.", ex);
        }
        return result;
    }

    @Override
    public List<Tour> findToursByParameters(String restType, String country, long startDate, int minDays, int maxPrice) throws DaoException {
        List<Tour> result = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.FIND_TOUR_BY_PARAMETERS)) {
            preparedStatement.setString(1, restType);
            preparedStatement.setString(2, country);
            preparedStatement.setLong(3, startDate);
            preparedStatement.setInt(4, minDays);
            preparedStatement.setInt(5, maxPrice);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tour tour = createTourFromResultSet(resultSet);
                result.add(tour);
            }
        } catch (SQLException ex) {
            throw new DaoException("Exception of finding tours by parameters.", ex);
        }
        return result;
    }

    @Override
    public List<Tour> findToursByCountry(String country) throws DaoException {
        List<Tour> result = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.FIND_TOUR_BY_COUNTRY)) {
            preparedStatement.setString(1, country);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tour tour = createTourFromResultSet(resultSet);
                result.add(tour);
            }
        } catch (SQLException ex) {
            throw new DaoException("Exception of finding tours by country.", ex);
        }
        return result;
    }

    @Override
    public List<Tour> findToursByType(String restType) throws DaoException {
        List<Tour> result = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.FIND_TOUR_BY_PURPOSE)) {
            preparedStatement.setString(1, restType);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tour tour = createTourFromResultSet(resultSet);
                result.add(tour);
            }
        } catch (SQLException ex) {
            throw new DaoException("Exception of finding tours by type.", ex);
        }
        return result;
    }

    @Override
    public List<Tour> findAllHotTours() throws DaoException {
        List<Tour> result = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.FIND_ALL_HOT_TOURS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tour tour = createTourFromResultSet(resultSet);
                tour.setDiscount(resultSet.getInt(ColumnName.DISCOUNT));
                result.add(tour);
            }
        } catch (SQLException ex) {
            throw new DaoException("Exception of finding all hot tours.", ex);
        }
        return result;
    }

    @Override
    public Set<String> findAvailableCountries() throws DaoException {
        Set<String> result = new HashSet<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.SELECT_AVAILABLE_COUNTRIES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getString(ColumnName.COUNTRY));
            }
        } catch (SQLException ex) {
            throw new DaoException("Exception of finding tour countries.", ex);
        }
        return result;
    }

    @Override
    public boolean createTour(Tour tour) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.CREATE_TOUR)) {
            preparedStatement.setString(1, tour.getTourType().name().toLowerCase());
            preparedStatement.setString(2, tour.getCountry());
            preparedStatement.setString(3, tour.getHotelName());
            preparedStatement.setString(4, tour.getHotelType().name().toLowerCase());
            preparedStatement.setString(5, tour.getTransport().name().toLowerCase());
            LocalDate localDate = tour.getStartDate();
            long date = DateTimeUtil.countLongFromLocalDate(localDate);
            preparedStatement.setLong(6, date);
            preparedStatement.setInt(7, tour.getDays());
            preparedStatement.setInt(8, tour.getPrice());
            preparedStatement.setInt(9, tour.getAvailableQuantity());
            preparedStatement.setString(10, tour.getDescription());
            preparedStatement.setString(11, tour.getImagePath());
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException("Exception of creating new tour.", ex);
        }
        return result;
    }


    private Tour createTourFromResultSet(ResultSet resultSet) throws SQLException {
        Tour tour = new Tour();
        tour.setId(resultSet.getInt(ColumnName.ID_TOUR));
        tour.setTourType(TourType.valueOf(resultSet.getString(ColumnName.TOUR_PURPOSE).toUpperCase()));
        tour.setCountry(resultSet.getString(ColumnName.COUNTRY));
        tour.setHotelName(resultSet.getString(ColumnName.HOTEL_NAME));
        tour.setHotelType(HotelType.valueOf(resultSet.getString(ColumnName.HOTEL_STARS).toUpperCase()));
        tour.setTransport(TransportType.valueOf(resultSet.getString(ColumnName.TRANSPORT).toUpperCase()));
        LocalDate date = DateTimeUtil.convertLocalDateFromLong(resultSet.getLong(ColumnName.DATE_START));
        tour.setStartDate(date);
        tour.setDays(resultSet.getInt(ColumnName.QUANTITY_OF_DAYS));
        tour.setPrice(resultSet.getInt(ColumnName.PRICE));
        tour.setAvailableQuantity(resultSet.getInt(ColumnName.QUANTITY_TOURS));
        tour.setDescription(resultSet.getString(ColumnName.DESCRIPTION));
        tour.setImagePath(resultSet.getString(ColumnName.IMAGE_PATH));
        return tour;
    }

}