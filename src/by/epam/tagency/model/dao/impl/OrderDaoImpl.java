package by.epam.tagency.model.dao.impl;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.model.connection.ConnectionPool;
import by.epam.tagency.model.dao.ColumnName;
import by.epam.tagency.model.dao.OrderDao;
import by.epam.tagency.model.dao.QuerySql;
import by.epam.tagency.model.entity.*;
import by.epam.tagency.util.DateTimeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            try (PreparedStatement psTour = connection.prepareStatement(QuerySql.FIND_TOUR_QUANTITY_BY_ID, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
                 PreparedStatement psTravelDocs = connection.prepareStatement(QuerySql.CREATE_EMPTY_TRAVEL_DOCS, PreparedStatement.RETURN_GENERATED_KEYS);
                 PreparedStatement psOrder = connection.prepareStatement(QuerySql.CREATE_ORDER)) {
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
            throw new DaoException("Connection exception in transaction of creating order in database", e);
        }
        return result;
    }

    @Override
    public boolean deleteOrder(int idOrder) throws DaoException {
        boolean result = false;
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement psOrder = connection.prepareStatement(QuerySql.FIND_ORDER_BY_ID, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
                 PreparedStatement psDocs = connection.prepareStatement(QuerySql.DELETE_TRAVEL_DOCS_BY_ID)) {
                connection.setAutoCommit(false);
                psOrder.setInt(1, idOrder);
                ResultSet rsOrder = psOrder.executeQuery();
                int idDocs = 0;
                if (rsOrder.next()) {
                    idDocs = rsOrder.getInt(ColumnName.ID_TRAVEL_DOCS_FK);
                    rsOrder.deleteRow();
                    logger.info("Deleted order " + idOrder);
                }
                if (idDocs > 0) {
                    psDocs.setInt(1, idDocs);
                    result = psDocs.executeUpdate() > 0;
                    logger.info("Deleted docs " + idDocs);
                }
                if (result) {
                    connection.commit();
                }
            } catch (SQLException e) {
                connection.rollback();
                throw new DaoException("Exception of deleting order in database " + idOrder, e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DaoException("Connection exception in transaction of deleting order in database", e);
        }
        return result;
    }

    @Override
    public List<ClientOrder> findAllOrdersWithValues(int idUser) throws DaoException {
        List<ClientOrder> orders = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.SELECT_ORDERS_INFO_BY_ID_USER)) {
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ClientOrder order = createOrderFromResultSet(resultSet);
                order.setComment(resultSet.getString(ColumnName.COMMENT));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception of finding all orders with values in database for user " + idUser, e);
        }
        return orders;
    }

    @Override
    public List<ClientOrder> findActualOrdersWithValues(int idUser) throws DaoException {
        List<ClientOrder> orders = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.SELECT_ACTUAL_ORDERS_BY_ID_USER)) {
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ClientOrder order = createOrderFromResultSet(resultSet);
                order.setComment(resultSet.getString(ColumnName.COMMENT));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception of finding actual orders with values in database for user " + idUser, e);
        }
        return orders;
    }

    @Override
    public ClientOrder findConcreteOrderWithValues(int idOrder) throws DaoException {
        ClientOrder order = new ClientOrder();
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.FIND_ORDER_BY_ID_ORDER)) {
            preparedStatement.setInt(1, idOrder);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = new ClientOrder();
                order.setId(idOrder);
                TravelDocs docs = new TravelDocs();
                docs.setId(resultSet.getInt(ColumnName.ID_TRAVEL_DOCS));
                order.setTravelDocs(docs);
                Tour tour = createTourForOrderFromResultSet(resultSet);
                order.setTour(tour);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception of finding concrete order in database " + idOrder, e);
        }
        return order;
    }

    @Override
    public Map<ClientOrder, String> findOrdersAndUsersToAddDocs() throws DaoException {
        Map<ClientOrder, String> usersAndOrders = new HashMap<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.FIND_ORDERS_WITH_USERS_TO_ADD_DOCS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ClientOrder order = createOrderFromResultSet(resultSet);
                order.getTravelDocs().setVoucher(resultSet.getString(ColumnName.VOUCHER));
                order.getTravelDocs().setInsurance(resultSet.getString(ColumnName.INSURANCE));
                order.getTravelDocs().setTicket(resultSet.getString(ColumnName.TICKET));
                String username = resultSet.getString(ColumnName.LOGIN);
                usersAndOrders.put(order, username);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception of finding orders and users to add docs in database", e);
        }
        return usersAndOrders;
    }

    @Override
    public Map<ClientOrder, String> findOrdersAndUsersToEditOrders() throws DaoException {
        Map<ClientOrder, String> usersAndOrders = new HashMap<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.FIND_ORDERS_WITH_USERS_TO_EDIT_ORDERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ClientOrder order = createOrderFromResultSet(resultSet);
                LocalDate birth = DateTimeUtil.convertLocalDateFromLong(resultSet.getLong(ColumnName.BIRTH_DATE));
                order.getPassport().setBirthDate(birth);
                order.getPassport().setPassportNumber(resultSet.getString(ColumnName.PASSPORT_NUMBER));
                order.getPassport().setPassportImage(resultSet.getString(ColumnName.PASSPORT_IMAGE));
                String username = resultSet.getString(ColumnName.LOGIN);
                usersAndOrders.put(order, username);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception of finding orders and users to edit orders in database", e);
        }
        return usersAndOrders;
    }

    @Override
    public boolean confirmOrder(int idOrder) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.SET_ORDER_CONFIRMED)) {
            preparedStatement.setInt(1, idOrder);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Exception of confirming order in database for user " + idOrder, e);
        }
        return result;
    }

    @Override
    public boolean declineOrder(int idOrder, String comment) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.SET_ORDER_DECLINED)) {
            preparedStatement.setString(1, comment);
            preparedStatement.setInt(2, idOrder);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Exception of declining order in database for order " + idOrder, e);
        }
        return result;
    }

    @Override
    public boolean addDocsOrderState(int idOrder) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.SET_ORDER_ADDED_DOCS)) {
            preparedStatement.setInt(1, idOrder);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Exception of changing order state to added docs in database for order " + idOrder, e);
        }
        return result;
    }

    @Override
    public boolean finishOrder(int idOrder, String comment) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.SET_ORDER_FINISHED)) {
            preparedStatement.setString(1, comment);
            preparedStatement.setInt(2, idOrder);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Exception of finishing order in database for order " + idOrder, e);
        }
        return result;
    }

    @Override
    public boolean payForOrder(int idOrder, int idUser, int sumToPay) throws DaoException {
        boolean result = false;
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement psSheet = connection.prepareStatement(QuerySql.FIND_SHEET_BY_ID_USER, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
                 PreparedStatement psOperation = connection.prepareStatement(QuerySql.CREATE_OPERATION);
                 PreparedStatement psOrder = connection.prepareStatement(QuerySql.SET_ORDER_PAID)) {
                connection.setAutoCommit(false);
                psSheet.setInt(1, idUser);
                ResultSet rsSheet = psSheet.executeQuery();
                boolean isPaidSheet = false;
                if (rsSheet.next()) {
                    int sheetSum = rsSheet.getInt(ColumnName.SHEET_SUM);
                    int resultSum = sheetSum - sumToPay;
                    if (resultSum > 0) {
                        int idSheet = rsSheet.getInt(ColumnName.ID_SHEET);
                        rsSheet.updateInt(ColumnName.SHEET_SUM, resultSum);
                        rsSheet.updateRow();
                        logger.info("Debit the sheet of user " + idUser);
                        psOperation.setInt(1, idSheet);
                        psOperation.setInt(2, resultSum);
                        psOperation.setString(3, OperationPurpose.PAY);
                        isPaidSheet = psOperation.executeUpdate() > 0;
                    }
                }
                if (isPaidSheet) {
                    psOrder.setInt(1, idOrder);
                    result = psOrder.executeUpdate() > 0;
                }
                if (result) {
                    connection.commit();
                }
            } catch (SQLException e) {
                connection.rollback();
                throw new DaoException("Exception of paying order in database for order " + idOrder + ", for user " + idUser, e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DaoException("Connection exception in transaction of paying order in database", e);
        }
        return result;
    }

    private ClientOrder createOrderFromResultSet(ResultSet resultSet) throws SQLException {
        ClientOrder order = new ClientOrder();
        order.setId(resultSet.getInt(ColumnName.ID_ORDER));
        LocalDateTime dateTime = DateTimeUtil.convertLocalDateTimeFromLong(resultSet.getLong(ColumnName.DATE_ORDER));
        order.setDateTimeOrder(dateTime);
        order.setOrderState(OrderState.valueOf(resultSet.getString(ColumnName.STATE).toUpperCase()));
        Tour tour = createTourForOrderFromResultSet(resultSet);
        order.setTour(tour);
        ClientPassport passport = new ClientPassport();
        passport.setSurname(resultSet.getString(ColumnName.SURNAME));
        passport.setName(resultSet.getString(ColumnName.NAME));
        order.setPassport(passport);
        TravelDocs docs = new TravelDocs();
        docs.setId(resultSet.getInt(ColumnName.ID_TRAVEL_DOCS));
        order.setTravelDocs(docs);
        return order;
    }

    private Tour createTourForOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Tour tour = new Tour();
        tour.setTourType(TourType.valueOf(resultSet.getString(ColumnName.TOUR_PURPOSE).toUpperCase()));
        tour.setCountry(resultSet.getString(ColumnName.COUNTRY));
        LocalDate date = DateTimeUtil.convertLocalDateFromLong(resultSet.getLong(ColumnName.DATE_START));
        tour.setStartDate(date);
        tour.setPrice(resultSet.getInt(ColumnName.PRICE));
        tour.setDiscount(resultSet.getInt(ColumnName.DISCOUNT));
        tour.setDays(resultSet.getInt(ColumnName.QUANTITY_OF_DAYS));
        return tour;
    }

}