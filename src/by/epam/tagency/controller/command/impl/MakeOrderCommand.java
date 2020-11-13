package by.epam.tagency.controller.command.impl;

import by.epam.tagency.controller.AttributeName;
import by.epam.tagency.controller.command.Command;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.Tour;
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
                TourService tourService = TourServiceImpl.getInstance();
                List<Tour> tours = tourService.findAllTours();
                session.setAttribute(AttributeName.ALL_TOURS, tours);
                request.setAttribute(AttributeName.CREATE_ORDER, true);
                logger.info("Order has been created.");
            } else {
                request.setAttribute(AttributeName.CREATE_ORDER, false);
                logger.warn("Order has not been created.");
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}