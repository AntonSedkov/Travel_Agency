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

public class SearchTourCommand implements Command {
    private static Logger logger = LogManager.getLogger(SearchTourCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String tourPurpose = request.getParameter(AttributeName.TOUR_PURPOSE);
        String country = request.getParameter(AttributeName.COUNTRY);
        String startDate = request.getParameter(AttributeName.START_DATE);
        String toutDays = request.getParameter(AttributeName.TOUR_DAYS);
        String maxPrice = request.getParameter(AttributeName.MAX_PRICE);
        TourService service = TourServiceImpl.getInstance();
        String page;
        try {
            List<Tour> tours = service.findToursByParameters(tourPurpose, country, startDate, toutDays, maxPrice);
            if (tours.size() > 0) {
                request.setAttribute(AttributeName.SEARCH_TOURS, tours);
            } else {
                request.setAttribute(AttributeName.SEARCH_TOURS_NOTHING, true);
            }
            page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
            logger.info("Search has been completed by parameters");
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}