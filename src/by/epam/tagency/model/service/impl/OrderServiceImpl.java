package by.epam.tagency.model.service.impl;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.dao.OrderDao;
import by.epam.tagency.model.dao.impl.OrderDaoImpl;
import by.epam.tagency.model.entity.ClientOrder;
import by.epam.tagency.model.entity.OrderState;
import by.epam.tagency.model.service.OrderService;
import by.epam.tagency.util.DateTimeUtil;
import by.epam.tagency.util.XssSafeUtil;
import by.epam.tagency.validator.GeneralValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private static final OrderServiceImpl INSTANCE = new OrderServiceImpl();
    private static Logger logger = LogManager.getLogger(OrderServiceImpl.class);
    private static final float DIVISOR_FOR_PERCENT = 100.0f;

    private OrderServiceImpl() {
    }

    public static OrderServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean createOrder(String idTour, String idPassport) throws ServiceException {
        boolean result = false;
        if (GeneralValidator.isDigitValue(idTour) && GeneralValidator.isDigitValue(idPassport)) {
            try {
                int idTourInt = Integer.parseInt(idTour);
                int idPassportInt = Integer.parseInt(idPassport);
                LocalDateTime dateTime = LocalDateTime.now();
                long dateTimeLong = DateTimeUtil.convertLongFromLocalDateTime(dateTime);
                OrderDao dao = OrderDaoImpl.getInstance();
                result = dao.createOrder(idTourInt, idPassportInt, dateTimeLong);
                logger.info("Created new order");
            } catch (NumberFormatException e) {
                throw new ServiceException("Incoming ID is wrong format - not an integer", e);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public boolean deleteOrder(String idOrder) throws ServiceException {
        boolean result = false;
        if (GeneralValidator.isDigitValue(idOrder)) {
            try {
                int idOrderInt = Integer.parseInt(idOrder);
                OrderDao dao = OrderDaoImpl.getInstance();
                result = dao.deleteOrder(idOrderInt);
                logger.info("Delete the order " + idOrderInt);
            } catch (NumberFormatException e) {
                throw new ServiceException("Incoming ID Order is wrong format - not an integer", e);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public List<ClientOrder> findAllOrdersWithValues(int idUser) throws ServiceException {
        List<ClientOrder> orders;
        try {
            OrderDao dao = OrderDaoImpl.getInstance();
            orders = dao.findAllOrdersWithValues(idUser);
            logger.info("Find all orders with values for user " + idUser);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public List<ClientOrder> findActualOrdersWithValues(int idUser) throws ServiceException {
        List<ClientOrder> orders;
        try {
            OrderDao dao = OrderDaoImpl.getInstance();
            orders = dao.findActualOrdersWithValues(idUser);
            logger.info("Find actual orders with values for user " + idUser);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public ClientOrder findConcreteOrderWithValues(String idOrder) throws ServiceException {
        ClientOrder order = new ClientOrder();
        if (GeneralValidator.isDigitValue(idOrder)) {
            int idOrderInt = Integer.parseInt(idOrder);
            OrderDao dao = OrderDaoImpl.getInstance();
            try {
                order = dao.findConcreteOrderWithValues(idOrderInt);
                logger.info("Find concrete order " + idOrder);
            } catch (NumberFormatException e) {
                throw new ServiceException("Incoming ID Order is wrong format - not an integer", e);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return order;
    }

    @Override
    public Map<ClientOrder, String> findOrdersAndUsersToAddDocs() throws ServiceException {
        Map<ClientOrder, String> usersAndOrders;
        try {
            OrderDao dao = OrderDaoImpl.getInstance();
            usersAndOrders = dao.findOrdersAndUsersToAddDocs();
            logger.info("Orders and users to add docs has been found.");
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return usersAndOrders;
    }

    @Override
    public Map<ClientOrder, String> findOrdersAndUsersToEditOrders() throws ServiceException {
        Map<ClientOrder, String> usersAndOrders;
        try {
            OrderDao dao = OrderDaoImpl.getInstance();
            usersAndOrders = dao.findOrdersAndUsersToEditOrders();
            logger.info("Orders and users to edit orders has been found.");
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return usersAndOrders;
    }

    @Override
    public boolean changeState(String idOrder, OrderState target, String... additionalParams) throws ServiceException {
        boolean result = false;
        if (GeneralValidator.isDigitValue(idOrder)) {
            try {
                int idOrderInt = Integer.parseInt(idOrder);
                OrderDao dao = OrderDaoImpl.getInstance();
                switch (target) {
                    case CONFIRMED -> result = dao.confirmOrder(idOrderInt);
                    case PAID -> {
                        if (additionalParams != null && additionalParams.length == 2) {
                            int idUserInt = Integer.parseInt(additionalParams[0]);
                            int sumToPay = Integer.parseInt(additionalParams[1]);
                            result = dao.payForOrder(idOrderInt, idUserInt, sumToPay);
                        }
                    }
                    case ADDED_DOCS -> result = dao.addDocsOrderState(idOrderInt);
                    case FINISHED -> {
                        if (additionalParams != null && additionalParams.length == 1) {
                            String safeComment = XssSafeUtil.xssSafeString(additionalParams[0]);
                            result = dao.finishOrder(idOrderInt, safeComment);
                        }
                    }
                    case DECLINED -> {
                        if (additionalParams != null && additionalParams.length == 1) {
                            String safeComment = XssSafeUtil.xssSafeString(additionalParams[0]);
                            result = dao.declineOrder(idOrderInt, safeComment);
                        }
                    }
                }
            } catch (NumberFormatException e) {
                throw new ServiceException("Incoming ID Order is wrong format - not an integer", e);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public Map<ClientOrder, Integer> createOrdersWithSumToPay(int idUser, List<ClientOrder> orders, int sheetDiscount) {
        Map<ClientOrder, Integer> ordersWithSum = new HashMap<>();
        if (orders != null) {
            List<ClientOrder> ordersToPay = orders.stream()
                    .filter(o -> o.getOrderState().equals(OrderState.CONFIRMED))
                    .collect(Collectors.toList());
            for (ClientOrder order : ordersToPay) {
                int orderSum = order.getTour().getPrice();
                int discountSheetSum = Math.round(orderSum * sheetDiscount / DIVISOR_FOR_PERCENT);
                int discountTourSum = Math.round(orderSum * order.getTour().getDiscount() / DIVISOR_FOR_PERCENT);
                int sumToPay = orderSum - discountSheetSum - discountTourSum;
                ordersWithSum.put(order, sumToPay);
            }
        }
        return ordersWithSum;
    }

}