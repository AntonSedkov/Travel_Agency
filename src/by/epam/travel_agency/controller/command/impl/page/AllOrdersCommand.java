package by.epam.travel_agency.controller.command.impl.page;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.entity.ClientOrder;
import by.epam.travel_agency.model.service.OrderService;
import by.epam.travel_agency.model.service.TourService;
import by.epam.travel_agency.model.service.impl.OrderServiceImpl;
import by.epam.travel_agency.model.service.impl.TourServiceImpl;
import by.epam.travel_agency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

public class AllOrdersCommand implements Command {
    private static Logger logger = LogManager.getLogger(AllOrdersCommand.class);

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
            page = PathManager.getProperty(PathManager.PAGE_USER_SEE_ORDERS);
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}