package by.epam.tagency.model.dao;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.model.entity.ClientOrder;

import java.util.List;
import java.util.Map;

/**
 * Interface for {@link ClientOrder} database interactions
 *
 * @author Anton Sedkov
 * @version 1.0
 */
public interface OrderDao extends BaseDao<Integer, ClientOrder> {

    /**
     * Create the order from concrete parameters in database
     *
     * @param idTour        reference to the identifier of the concrete tour
     * @param idPassport    reference to the identifier of the concrete passport
     * @param dateTimeOrder reference to the time of order creation
     * @return {@code true} if order creation is successful; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     * @see by.epam.tagency.model.entity.Tour
     * @see by.epam.tagency.model.entity.ClientPassport
     */
    boolean createOrder(int idTour, int idPassport, long dateTimeOrder) throws DaoException;

    /**
     * Delete the concrete order in database
     *
     * @param idOrder reference to the identifier of the concrete order
     * @return {@code true} if order deletion is successful; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    boolean deleteOrder(int idOrder) throws DaoException;

    /**
     * Search for all orders filled with specific values for the concrete user in database
     *
     * @param idUser reference to the identifier of the concrete user
     * @return {@link List} of orders filled with specific values
     * @throws DaoException when {@link java.sql.SQLException} has happened
     * @see by.epam.tagency.model.entity.User
     */
    List<ClientOrder> findAllOrdersWithValues(int idUser) throws DaoException;

    /**
     * Search for actual orders filled with specific values for the concrete user in database
     *
     * @param idUser reference to the identifier of the concrete user
     * @return {@link List} of orders filled with specific values
     * @throws DaoException when {@link java.sql.SQLException} has happened
     * @see by.epam.tagency.model.entity.User
     */
    List<ClientOrder> findActualOrdersWithValues(int idUser) throws DaoException;

    /**
     * Search for the concrete order filled with specific values in database
     *
     * @param idOrder reference to the identifier of the concrete order
     * @return concrete {@code ClientOrder} filled with specific values
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    ClientOrder findConcreteOrderWithValues(int idOrder) throws DaoException;

    /**
     * Search for orders in state {@code OrderState.PAID} and corresponding usernames in database
     *
     * @return {@link Map} of orders and usernames where key is {@code ClientOrder} and value is {@code String} username
     * @throws DaoException when {@link java.sql.SQLException} has happened
     * @see by.epam.tagency.model.entity.OrderState
     */
    Map<ClientOrder, String> findOrdersAndUsersToAddDocs() throws DaoException;

    /**
     * Search for orders in state {@code OrderState.NEW} and corresponding usernames in database
     *
     * @return {@link Map} of orders and usernames where key is {@code ClientOrder} and value is {@code String} username
     * @throws DaoException when {@link java.sql.SQLException} has happened
     * @see by.epam.tagency.model.entity.OrderState
     */
    Map<ClientOrder, String> findOrdersAndUsersToEditOrders() throws DaoException;

    /**
     * Change state for concrete order to {@code OrderState.CONFIRMED} in database
     *
     * @param idOrder reference to the identifier of the concrete order
     * @return {@code true} if state changing of order is successful; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     * @see by.epam.tagency.model.entity.OrderState
     */
    boolean confirmOrder(int idOrder) throws DaoException;

    /**
     * Change state for concrete order to {@code OrderState.DECLINED} in database
     *
     * @param idOrder reference to the identifier of the concrete order
     * @param comment explain the reason of declining this order
     * @return {@code true} if state changing of order is successful; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     * @see by.epam.tagency.model.entity.OrderState
     */
    boolean declineOrder(int idOrder, String comment) throws DaoException;

    /**
     * Change state for concrete order to {@code OrderState.ADDED_DOCS} in database
     *
     * @param idOrder reference to the identifier of the concrete order
     * @return {@code true} if state changing of order is successful; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     * @see by.epam.tagency.model.entity.OrderState
     */
    boolean addDocsOrderState(int idOrder) throws DaoException;

    /**
     * Change state for concrete order to {@code OrderState.FINISHED} in database
     *
     * @param idOrder reference to the identifier of the concrete order
     * @param comment is the user recall after order finish
     * @return {@code true} if state changing of order is successful; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     * @see by.epam.tagency.model.entity.OrderState
     */
    boolean finishOrder(int idOrder, String comment) throws DaoException;

    /**
     * Method provides one transaction in database:
     * Checking for the sum of the user sheet is enough for the payment,
     * Payment from the sheet for the concrete order,
     * Change state for the order to {@code OrderState.PAID}
     *
     * @param idOrder  reference to the identifier of the concrete order
     * @param idUser   reference to the identifier of the concrete user
     * @param sumToPay define the sum of payment
     * @return {@code true} if transaction for payment is successful; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     * @see by.epam.tagency.model.entity.User
     * @see by.epam.tagency.model.entity.OrderState
     */
    boolean payForOrder(int idOrder, int idUser, int sumToPay) throws DaoException;

}