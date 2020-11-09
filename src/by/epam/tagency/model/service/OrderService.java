package by.epam.tagency.model.service;

import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.ClientOrder;
import by.epam.tagency.model.entity.OrderState;

import java.util.List;
import java.util.Map;

public interface OrderService {

    boolean createOrder(String idTour, String idPassport) throws ServiceException;

    boolean deleteOrder(String idOrder) throws ServiceException;

    List<ClientOrder> findAllOrdersWithValues(int idUser) throws ServiceException;

    List<ClientOrder> findActualOrdersWithValues(int idUser) throws ServiceException;

    ClientOrder findConcreteOrderWithValues(String idOrder) throws ServiceException;

    Map<ClientOrder, String> findOrdersAndUsersToAddDocs() throws ServiceException;

    Map<ClientOrder, String> findOrdersAndUsersToEditOrders() throws ServiceException;

    boolean changeState(String idOrder, OrderState target, String... additionalParams) throws ServiceException;

    Map<ClientOrder, Integer> createOrdersWithSumToPay(int idUser, List<ClientOrder> orders, int sheetDiscount);

    // List<ClientOrder> findOrdersWithValuesByState(int idUser, String state) throws ServiceException;
}