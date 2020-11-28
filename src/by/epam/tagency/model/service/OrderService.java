package by.epam.tagency.model.service;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.ClientOrder;
import by.epam.tagency.model.entity.OrderState;

import java.util.List;
import java.util.Map;

/**
 * Interface for {@link ClientOrder} service actions
 *
 * @author Anton Sedkov
 * @version 1.0
 */
public interface OrderService {

    /**
     * Validate incoming parameters and create the order with this parameters
     *
     * @param idTour     reference to the identifier of the concrete tour
     * @param idPassport reference to the identifier of the concrete passport
     * @return {@code true} if order creation is successful; {@code false} otherwise
     * @throws ServiceException when {@link DaoException} has happened or wrong format of incoming parameter
     * @see by.epam.tagency.model.entity.Tour
     * @see by.epam.tagency.model.entity.ClientPassport
     */
    boolean createOrder(String idTour, String idPassport) throws ServiceException;

    /**
     * Validate incoming parameter and delete the concrete order
     *
     * @param idOrder reference to the identifier of the concrete order
     * @return {@code true} if order deletion is successful; {@code false} otherwise
     * @throws ServiceException when {@link DaoException} has happened or wrong format of incoming parameter
     */
    boolean deleteOrder(String idOrder) throws ServiceException;

    /**
     * Search for all orders filled with specific values for the concrete user
     *
     * @param idUser reference to the identifier of the concrete user
     * @return {@link List} of orders filled with specific values
     * @throws ServiceException when {@link DaoException} has happened
     * @see by.epam.tagency.model.entity.User
     */
    List<ClientOrder> findAllOrdersWithValues(int idUser) throws ServiceException;

    /**
     * Search for actual orders filled with specific values for the concrete user
     *
     * @param idUser reference to the identifier of the concrete user
     * @return {@link List} of orders filled with specific values
     * @throws ServiceException when {@link DaoException} has happened
     * @see by.epam.tagency.model.entity.User
     */
    List<ClientOrder> findActualOrdersWithValues(int idUser) throws ServiceException;

    /**
     * Validate incoming parameter and search for the concrete order filled with specific values
     *
     * @param idOrder reference to the identifier of the concrete order
     * @return concrete {@code ClientOrder} filled with specific values
     * @throws ServiceException when {@link DaoException} has happened or wrong format of incoming parameter
     */
    ClientOrder findConcreteOrderWithValues(String idOrder) throws ServiceException;

    /**
     * Search for orders in state {@code OrderState.PAID} and corresponding usernames
     *
     * @return {@link Map} of orders and usernames where key is {@code ClientOrder} and value is {@code String} username
     * @throws ServiceException when {@link DaoException} has happened
     * @see by.epam.tagency.model.entity.OrderState
     */
    Map<ClientOrder, String> findOrdersAndUsersToAddDocs() throws ServiceException;

    /**
     * Search for orders in state {@code OrderState.NEW} and corresponding usernames
     *
     * @return {@link Map} of orders and usernames where key is {@code ClientOrder} and value is {@code String} username
     * @throws ServiceException when {@link DaoException} has happened
     * @see by.epam.tagency.model.entity.OrderState
     */
    Map<ClientOrder, String> findOrdersAndUsersToEditOrders() throws ServiceException;

    /**
     * Validate incoming identifier of order and make further actions depending on incoming target {@link OrderState}
     * with validations of necessary parameters for action
     *
     * @param idOrder          reference to the identifier of the concrete order
     * @param target           reference to the further action {@code OrderState}
     * @param additionalParams is parameters necessary for target actions
     * @return {@code true} if state actions is successful; {@code false} otherwise
     * @throws ServiceException when {@link DaoException} has happened or wrong format of incoming parameter
     */
    boolean changeState(String idOrder, OrderState target, String... additionalParams) throws ServiceException;

    /**
     * Create orders with calculated sum depending on discount of tour and discount of sheet
     *
     * @param orders        is the {@code List} of orders
     * @param sheetDiscount is the discount of the sheet
     * @return {@link Map} of orders and calculated sum where key is {@code ClientOrder} and value is {@code Integer} calculated sum
     */
    Map<ClientOrder, Integer> createOrdersWithSumToPay(List<ClientOrder> orders, int sheetDiscount);

}