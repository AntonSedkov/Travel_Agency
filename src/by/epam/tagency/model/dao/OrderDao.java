package by.epam.tagency.model.dao;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.model.entity.ClientOrder;

import java.util.List;
import java.util.Map;

public interface OrderDao {

    boolean createOrder(int idTour, int idPassport, long dateTimeOrder) throws DaoException;

    boolean deleteOrder(int idOrder) throws DaoException;

    List<ClientOrder> findAllOrdersWithValues(int idUser) throws DaoException;

    List<ClientOrder> findActualOrdersWithValues(int idUser) throws DaoException;

    ClientOrder findConcreteOrderWithValues(int idOrder) throws DaoException;

    List<ClientOrder> findOrdersWithValuesByState(int idUser, String state) throws DaoException;

    Map<ClientOrder, String> findOrdersAndUsersToAddDocs() throws DaoException;

}