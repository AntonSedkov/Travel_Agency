package by.epam.travel_agency.model.dao.impl;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.model.connection.ConnectionPool;
import by.epam.travel_agency.model.dao.ColumnName;
import by.epam.travel_agency.model.dao.OrderDao;
import by.epam.travel_agency.model.dao.StatementSql;
import by.epam.travel_agency.model.entity.*;
import by.epam.travel_agency.util.DateTimeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static Logger logger = LogManager.getLogger(OrderDaoImpl.class);
    private static final OrderDaoImpl INSTANCE = new OrderDaoImpl();
    private static ConnectionPool pool = ConnectionPool.INSTANCE;

    private OrderDaoImpl() {
    }

    public static OrderDaoImpl getInstance() {
        return INSTANCE;
    }


    @Override
    public boolean createOrder(int idTour, int idPassport, long dateTimeOrder) throws DaoException {
        boolean result = false;
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement psTour = connection.prepareStatement(StatementSql.FIND_TOUR_QUANTITY_BY_ID, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
                 PreparedStatement psTravelDocs = connection.prepareStatement(StatementSql.CREATE_EMPTY_TRAVEL_DOCS, PreparedStatement.RETURN_GENERATED_KEYS);
                 PreparedStatement psOrder = connection.prepareStatement(StatementSql.CREATE_ORDER)) {
                connection.setAutoCommit(false);
                psTour.setInt(1, idTour);
                ResultSet rsTour = psTour.executeQuery();
                int quantityTour = -1;
                if (rsTour.next()) {
                    quantityTour = rsTour.getInt(ColumnName.QUANTITY_TOURS);
                    quantityTour--;
                    rsTour.updateInt(ColumnName.QUANTITY_TOURS, quantityTour);
                    rsTour.updateRow();
                    logger.info("Tours left : " + quantityTour);
                }
                int idTravelDocs = 0;
                if (quantityTour > -1) {
                    psTravelDocs.executeUpdate();
                    ResultSet rsIdTravelDocs = psTravelDocs.getGeneratedKeys();
                    if (rsIdTravelDocs.next()) {
                        idTravelDocs = rsIdTravelDocs.getInt(1);
                        logger.info("TravelDocs create and id travel docs is " + idTravelDocs);
                    }
                }
                if (idTravelDocs > 0) {
                    psOrder.setInt(1, idTour);
                    psOrder.setInt(2, idPassport);
                    psOrder.setInt(3, idTravelDocs);
                    psOrder.setLong(4, dateTimeOrder);
                    result = psOrder.executeUpdate() > 0;
                }
                if (result) {
                    connection.commit();
                }
            } catch (SQLException e) {
                connection.rollback();
                throw new DaoException("Exception of creating order in database", e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<ClientOrder> findAllOrdersWithValues(int idUser) throws DaoException {
        List<ClientOrder> orders = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.SELECT_ORDERS_INFO_BY_ID_USER)) {
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ClientOrder order = new ClientOrder();
                order.setIdTour(resultSet.getInt(ColumnName.ID_TOUR));
                order.setIdPassport(resultSet.getInt(ColumnName.ID_PASSPORT));
                order.setIdTravelDoc(resultSet.getInt(ColumnName.ID_TRAVEL_DOCS));
                LocalDateTime dateTime = DateTimeUtil.convertLocalDateTimeFromLong(resultSet.getLong(ColumnName.DATE_ORDER));
                order.setDateTimeOrder(dateTime);
                order.setOrderState(OrderState.valueOf(resultSet.getString(ColumnName.STATE).toUpperCase()));
                Tour tour = new Tour();
                tour.setTourType(TourType.valueOf(resultSet.getString(ColumnName.TOUR_PURPOSE).toUpperCase()));
                tour.setCountry(resultSet.getString(ColumnName.COUNTRY));
                LocalDate date = DateTimeUtil.convertLocalDateFromLong(resultSet.getLong(ColumnName.DATE_START));
                tour.setStartDate(date);
                tour.setPrice(resultSet.getInt(ColumnName.PRICE));
                ClientPassport passport = new ClientPassport();
                passport.setSurname(resultSet.getString(ColumnName.SURNAME));
                passport.setName(resultSet.getString(ColumnName.NAME));
                order.setTour(tour);
                order.setPassport(passport);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orders;
    }

    @Override
    public List<ClientOrder> findActualOrdersWithValues(int idUser) throws DaoException {
        return null;
    }

    @Override
    public List<ClientOrder> findOrdersWithValuesByState(int idUser, String state) throws DaoException {
        return null;
    }

    @Override
    public List<ClientOrder> findConcreteOrderWithValues(int idUser, int idOrder) throws DaoException {
        return null;
    }

    @Override
    public boolean doConfirm() throws DaoException {
        return false;
    }

    @Override
    public boolean doPaid() throws DaoException {
        return false;
    }

    @Override
    public boolean doAddDocs() throws DaoException {
        return false;
    }

    @Override
    public boolean doFinished() throws DaoException {
        return false;
    }

}