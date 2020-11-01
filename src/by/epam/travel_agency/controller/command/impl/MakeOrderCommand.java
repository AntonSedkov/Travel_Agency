package by.epam.travel_agency.controller.command.impl;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.service.OrderService;
import by.epam.travel_agency.model.service.impl.OrderServiceImpl;
import by.epam.travel_agency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MakeOrderCommand implements Command {
    private static Logger logger = LogManager.getLogger(MakeOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        String idTour = request.getParameter(AttributeName.ID_TOUR);
        String idPassport = request.getParameter(AttributeName.ID_PASSPORT);
        OrderService orderService = OrderServiceImpl.getInstance();
        try {
            if (orderService.createOrder(idTour, idPassport)) {
                request.setAttribute(AttributeName.CREATE_ORDER, true);
                logger.info("Order has been created.");
            } else {
                request.setAttribute(AttributeName.CREATE_ORDER, false);
                logger.warn("Order has not been created.");
            }
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}