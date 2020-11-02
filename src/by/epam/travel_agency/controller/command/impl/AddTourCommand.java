package by.epam.travel_agency.controller.command.impl;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.service.TourService;
import by.epam.travel_agency.model.service.impl.TourServiceImpl;
import by.epam.travel_agency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddTourCommand implements Command {
    private static Logger logger = LogManager.getLogger(AddTourCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        TourService service = TourServiceImpl.getInstance();
        String tourType = request.getParameter(AttributeName.TOUR_TYPE);
        String country = request.getParameter(AttributeName.COUNTRY);
        String hotelName = request.getParameter(AttributeName.HOTEL_NAME);
        String hotelStars = request.getParameter(AttributeName.HOTEL_STARS);
        String transport = request.getParameter(AttributeName.TRANSPORT);
        String startDate = request.getParameter(AttributeName.START_DATE);
        String tourDays = request.getParameter(AttributeName.TOUR_DAYS);
        String tourPrice = request.getParameter(AttributeName.TOUR_PRICE);
        String quantityTours = request.getParameter(AttributeName.QUANTITY_TOURS);
        String description = request.getParameter(AttributeName.DESCRIPTION);
        String imageName = (String) request.getAttribute(AttributeName.IMAGE_NAME);
        try {
            if (service.createTour(tourType, country, hotelName, hotelStars, transport, startDate, tourDays, tourPrice, quantityTours, description, imageName)) {
                request.setAttribute(AttributeName.CREATE_TOUR, true);
                logger.info("Tour has been created.");
            } else {
                request.setAttribute(AttributeName.CREATE_TOUR, false);
                logger.warn("Tour has not been created.");
            }
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}