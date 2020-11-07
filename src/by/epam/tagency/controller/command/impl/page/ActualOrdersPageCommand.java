package by.epam.tagency.controller.command.impl.page;

import by.epam.tagency.controller.AttributeName;
import by.epam.tagency.controller.command.Command;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.ClientOrder;
import by.epam.tagency.model.service.OrderService;
import by.epam.tagency.model.service.TourService;
import by.epam.tagency.model.service.impl.OrderServiceImpl;
import by.epam.tagency.model.service.impl.TourServiceImpl;
import by.epam.tagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

public class ActualOrdersPageCommand implements Command {
    private static Logger logger = LogManager.getLogger(ActualOrdersPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int idUser = (int) session.getAttribute(AttributeName.ID_USER);
        String page;
        TourService tourService = TourServiceImpl.getInstance();
        OrderService orderService = OrderServiceImpl.getInstance();
        try {
            List<ClientOrder> orders = orderService.findAllOrdersWithValues(idUser);
            session.setAttribute(AttributeName.ORDERS, orders);
            Set<String> countries = tourService.findAvailableCountries();
            session.setAttribute(AttributeName.COUNTRIES, countries);
            page = PathManager.getProperty(PathManager.PAGE_USER_ORDERS_ACTUAL);
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}