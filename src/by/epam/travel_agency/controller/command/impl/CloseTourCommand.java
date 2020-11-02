package by.epam.travel_agency.controller.command.impl;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.entity.Tour;
import by.epam.travel_agency.model.service.TourService;
import by.epam.travel_agency.model.service.impl.TourServiceImpl;
import by.epam.travel_agency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CloseTourCommand implements Command {
    private static Logger logger = LogManager.getLogger(CloseTourCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        TourService service = TourServiceImpl.getInstance();
        String idTour = request.getParameter(AttributeName.ID_TOUR);
        try {
            if (service.closeTour(idTour)) {
                request.setAttribute(AttributeName.CLOSE_TOUR, true);
                List<Tour> tours = service.findAllTours();
                session.setAttribute(AttributeName.ALL_TOURS, tours);
                logger.info("Tour has been closed.");
            } else {
                request.setAttribute(AttributeName.CLOSE_TOUR, false);
                logger.warn("Tour has not been closed.");
            }
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}