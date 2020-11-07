package by.epam.tagency.controller.command.impl;

import by.epam.tagency.controller.AttributeName;
import by.epam.tagency.controller.command.Command;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.Tour;
import by.epam.tagency.model.service.TourService;
import by.epam.tagency.model.service.impl.TourServiceImpl;
import by.epam.tagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SetHotTourCommand implements Command {
    private static Logger logger = LogManager.getLogger(SetHotTourCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        TourService service = TourServiceImpl.getInstance();
        String idTour = request.getParameter(AttributeName.ID_TOUR);
        String discount = request.getParameter(AttributeName.DISCOUNT);
        try {
            if (service.setHotTour(idTour, discount)) {
                request.setAttribute(AttributeName.HOT_TOUR_GOOD, true);
                List<Tour> tours = service.findAllTours();
                session.setAttribute(AttributeName.ALL_TOURS, tours);
                logger.info("Tour has been discounted.");
            } else {
                request.setAttribute(AttributeName.HOT_TOUR_GOOD, false);
                logger.warn("Tour has not been discounted.");
            }
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}