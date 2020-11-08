package by.epam.tagency.controller.command.impl;

import by.epam.tagency.controller.AttributeName;
import by.epam.tagency.controller.command.Command;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.ClientOrder;
import by.epam.tagency.model.service.OrderService;
import by.epam.tagency.model.service.impl.OrderServiceImpl;
import by.epam.tagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class ConfirmOrderStateCommand implements Command {
    private static Logger logger = LogManager.getLogger(ConfirmOrderStateCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String idOrder = request.getParameter(AttributeName.ID_ORDER);
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        OrderService service = OrderServiceImpl.getInstance();
        try {
            if (service.confirmOrder(idOrder)) {
                request.setAttribute(AttributeName.CONFIRM_ORDER, true);
                Map<ClientOrder, String> ordersAndUsers = service.findOrdersAndUsersToEditOrders();
                session.setAttribute(AttributeName.ORDERS_AND_USERS, ordersAndUsers);
                logger.info("Confirming order is complete for " + idOrder);
            } else {
                request.setAttribute(AttributeName.CONFIRM_ORDER, false);
                logger.warn("Confirming order is failed for " + idOrder);
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}