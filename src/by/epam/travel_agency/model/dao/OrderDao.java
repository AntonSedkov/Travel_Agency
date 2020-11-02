package by.epam.travel_agency.model.dao;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.model.entity.ClientOrder;

import java.util.List;

public interface OrderDao {

    boolean createOrder(int idTour, int idPassport, long dateTimeOrder) throws DaoException;

    List<ClientOrder> findAllOrdersWithValues(int idUser) throws DaoException;

    List<ClientOrder> findActualOrdersWithValues(int idUser) throws DaoException;

    List<ClientOrder> findOrdersWithValuesByState(int idUser, String state) throws DaoException;

    List<ClientOrder> findConcreteOrderWithValues(int idUser, int idOrder) throws DaoException;

}