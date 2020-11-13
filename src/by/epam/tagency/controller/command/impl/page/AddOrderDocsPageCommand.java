package by.epam.tagency.controller.command.impl.page;

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
import java.util.Map;

public class AddOrderDocsPageCommand implements Command {
    private static Logger logger = LogManager.getLogger(AddOrderDocsPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        OrderService service = OrderServiceImpl.getInstance();
        Map<ClientOrder, String> ordersAndUsers;
        String page;
        try {
            ordersAndUsers = service.findOrdersAndUsersToAddDocs();
            request.getSession().setAttribute(AttributeName.ORDERS_AND_USERS, ordersAndUsers);
            page = PathManager.getProperty(PathManager.PAGE_MODERATOR_ADD_ORDER_DOCS);
            logger.info("Add order docs page created.");
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}