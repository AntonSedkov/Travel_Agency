package by.epam.tagency.controller.command.impl;

import by.epam.tagency.controller.AttributeName;
import by.epam.tagency.controller.command.Command;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.ClientOrder;
import by.epam.tagency.model.entity.OrderState;
import by.epam.tagency.model.service.OrderService;
import by.epam.tagency.model.service.impl.OrderServiceImpl;
import by.epam.tagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class ChangeOrderStateCommand implements Command {
    private static Logger logger = LogManager.getLogger(ChangeOrderStateCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String idOrder = request.getParameter(AttributeName.ID_ORDER);
        String targetState = request.getParameter(AttributeName.TARGET_STATE);
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        OrderService service = OrderServiceImpl.getInstance();
        try {
            switch (OrderState.valueOf(targetState.toUpperCase())) {
                case CONFIRMED -> {
                    if (service.changeState(idOrder, OrderState.CONFIRMED)) {
                        request.setAttribute(AttributeName.CONFIRM_ORDER, true);
                        Map<ClientOrder, String> ordersAndUsers = service.findOrdersAndUsersToEditOrders();
                        session.setAttribute(AttributeName.ORDERS_AND_USERS, ordersAndUsers);
                        logger.info("Confirming order is complete for " + idOrder);
                    } else {
                        request.setAttribute(AttributeName.CONFIRM_ORDER, false);
                        logger.warn("Confirming order is failed for " + idOrder);
                    }
                }
                case PAID -> {
                }
                case ADDED_DOCS -> {
                    if (service.changeState(idOrder, OrderState.ADDED_DOCS)) {
                        request.setAttribute(AttributeName.ADD_DOCS_ORDER, true);
                        Map<ClientOrder, String> ordersAndUsers = service.findOrdersAndUsersToAddDocs();
                        session.setAttribute(AttributeName.ORDERS_AND_USERS, ordersAndUsers);
                        logger.info("Changing state to added docs is complete for order " + idOrder);
                    } else {
                        request.setAttribute(AttributeName.ADD_DOCS_ORDER, false);
                        logger.warn("Changing state to added docs is failed for order " + idOrder);
                    }
                }
                case FINISHED -> {
                    String comment = request.getParameter(AttributeName.COMMENT);
                }
                case DECLINED -> {
                    String comment = request.getParameter(AttributeName.COMMENT);
                    if (service.changeState(idOrder, OrderState.DECLINED, comment)) {
                        request.setAttribute(AttributeName.DECLINE_ORDER, true);
                        Map<ClientOrder, String> ordersAndUsers = service.findOrdersAndUsersToEditOrders();
                        session.setAttribute(AttributeName.ORDERS_AND_USERS, ordersAndUsers);
                        logger.info("Declining order is complete for " + idOrder);
                    } else {
                        request.setAttribute(AttributeName.DECLINE_ORDER, false);
                        logger.warn("Declining order is failed for " + idOrder);
                    }
                }
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}