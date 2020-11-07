package by.epam.tagency.model.service;

import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.ClientOrder;

import java.util.List;

public interface OrderService {

    boolean createOrder(String idTour, String idPassport) throws ServiceException;

    boolean deleteOrder(String idOrder) throws ServiceException;

    List<ClientOrder> findAllOrdersWithValues(int idUser) throws ServiceException;

    List<ClientOrder> findActualOrdersWithValues(int idUser) throws ServiceException;

    List<ClientOrder> findOrdersWithValuesByState(int idUser, String state) throws ServiceException;

    List<ClientOrder> findConcreteOrderWithValues(int idUser, int idOrder) throws ServiceException;

}