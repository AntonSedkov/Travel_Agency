package by.epam.travel_agency.controller.command.impl;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.entity.Tour;
import by.epam.travel_agency.model.entity.TourType;
import by.epam.travel_agency.model.service.TourService;
import by.epam.travel_agency.model.service.impl.TourServiceImpl;
import by.epam.travel_agency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

public class SearchTourCommand implements Command {
    private static Logger logger = LogManager.getLogger(SearchTourCommand.class);
    private static final String DEFAULT_TOUR_PURPOSE = TourType.REST.name().toLowerCase();
    private static final String DEFAULT_COUNTRY = "Belarus";
    private static final String DEFAULT_START_DATE = LocalDate.now().toString();
    private static final String DEFAULT_TOUR_DAYS = "1";
    private static final String DEFAULT_MAX_PRICE = "1000000";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String tourPurpose = request.getParameter(AttributeName.TOUR_PURPOSE);
        session.setAttribute(AttributeName.SEARCH_TOURS_NOTHING, "false");
        session.setAttribute(AttributeName.SEARCH_TOURS, null);
        tourPurpose = (tourPurpose != null) ? tourPurpose : DEFAULT_TOUR_PURPOSE;
        String country = request.getParameter(AttributeName.COUNTRY);
        country = (country != null) ? country : DEFAULT_COUNTRY;
        String startDate = request.getParameter(AttributeName.START_DATE);
        startDate = (startDate != null) ? startDate : DEFAULT_START_DATE;
        String toutDays = request.getParameter(AttributeName.TOUR_DAYS);
        toutDays = (toutDays != null) ? toutDays : DEFAULT_TOUR_DAYS;
        String maxPrice = request.getParameter(AttributeName.MAX_PRICE);
        maxPrice = (maxPrice != null) ? maxPrice : DEFAULT_MAX_PRICE;
        TourService service = TourServiceImpl.getInstance();
        String page;
        try {
            List<Tour> tours = service.findToursByParameters(tourPurpose,country,startDate,toutDays,maxPrice);
            if (tours.size()>0){
                session.setAttribute(AttributeName.SEARCH_TOURS, tours);
            }else {
                session.setAttribute(AttributeName.SEARCH_TOURS_NOTHING, "true");
            }
            page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
            logger.info("Search has been complete by parameters");
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}