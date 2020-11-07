package by.epam.tagency.model.dao;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.model.entity.ClientOrder;

import java.util.List;

public interface OrderDao {

    boolean createOrder(int idTour, int idPassport, long dateTimeOrder) throws DaoException;

    boolean deleteOrder(int idOrder) throws DaoException;

    List<ClientOrder> findAllOrdersWithValues(int idUser) throws DaoException;

    List<ClientOrder> findActualOrdersWithValues(int idUser) throws DaoException;

    List<ClientOrder> findOrdersWithValuesByState(int idUser, String state) throws DaoException;

    List<ClientOrder> findConcreteOrderWithValues(int idUser, int idOrder) throws DaoException;

}