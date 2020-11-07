package by.epam.tagency.model.dao.impl;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.model.connection.ConnectionPool;
import by.epam.tagency.model.dao.ColumnName;
import by.epam.tagency.model.dao.QuerySql;
import by.epam.tagency.model.dao.TourDao;
import by.epam.tagency.model.entity.HotelType;
import by.epam.tagency.model.entity.Tour;
import by.epam.tagency.model.entity.TourType;
import by.epam.tagency.model.entity.TransportType;
import by.epam.tagency.util.DateTimeUtil;

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
        List<Tour> tours = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.FIND_ALL_TOURS_ACTIVE)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tour tour = createTourFromResultSet(resultSet);
                tours.add(tour);
            }
        } catch (SQLException ex) {
            throw new DaoException("Exception of finding all tours.", ex);
        }
        return tours;
    }

    @Override
    public Tour findTourById(int idConcreteTour) throws DaoException {
        Tour tour = new Tour();
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.FIND_TOUR_BY_ID)) {
            preparedStatement.setInt(1, idConcreteTour);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tour = createTourFromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            throw new DaoException("Exception of finding tour by id.", ex);
        }
        return tour;
    }

    @Override
    public List<Tour> findToursByParameters(String restType, String country, long startDate, int minDays, int maxPrice) throws DaoException {
        List<Tour> result = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.FIND_TOUR_BY_PARAMETERS)) {
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
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.FIND_TOUR_BY_COUNTRY)) {
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
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.FIND_TOUR_BY_PURPOSE)) {
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
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.FIND_ALL_HOT_TOURS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tour tour = createTourFromResultSet(resultSet);
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
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.SELECT_AVAILABLE_COUNTRIES)) {
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
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.CREATE_TOUR)) {
            preparedStatement.setString(1, tour.getTourType().name().toLowerCase());
            preparedStatement.setString(2, tour.getCountry());
            preparedStatement.setString(3, tour.getHotelName());
            preparedStatement.setString(4, tour.getHotelType().name().toLowerCase());
            preparedStatement.setString(5, tour.getTransport().name().toLowerCase());
            LocalDate localDate = tour.getStartDate();
            long date = DateTimeUtil.convertLongFromLocalDate(localDate);
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

    @Override
    public boolean closeTour(int idTour) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.CLOSE_TOUR)) {
            preparedStatement.setInt(1, idTour);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException("Exception of closing tour (setting zero quantity).", ex);
        }
        return result;
    }

    @Override
    public boolean setHotTour(int idTour, int discount) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.SET_TOUR_HOT)) {
            preparedStatement.setInt(1, discount);
            preparedStatement.setInt(2, idTour);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException("Exception of discounting the tour (setting discount).", ex);
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
        tour.setDiscount(resultSet.getInt(ColumnName.DISCOUNT));
        return tour;
    }

}