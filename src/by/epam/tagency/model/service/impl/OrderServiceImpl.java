package by.epam.tagency.model.service.impl;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.dao.OrderDao;
import by.epam.tagency.model.dao.impl.OrderDaoImpl;
import by.epam.tagency.model.entity.ClientOrder;
import by.epam.tagency.model.service.OrderService;
import by.epam.tagency.util.DateTimeUtil;
import by.epam.tagency.validator.GeneralValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static final OrderServiceImpl INSTANCE = new OrderServiceImpl();
    private static Logger logger = LogManager.getLogger(OrderServiceImpl.class);

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
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public List<ClientOrder> findActualOrdersWithValues(int idUser) throws ServiceException {
        return null;
    }

    @Override
    public List<ClientOrder> findOrdersWithValuesByState(int idUser, String state) throws ServiceException {
        return null;
    }

    @Override
    public List<ClientOrder> findConcreteOrderWithValues(int idUser, int idOrder) throws ServiceException {
        return null;
    }

}