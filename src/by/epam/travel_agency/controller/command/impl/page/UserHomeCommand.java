package by.epam.travel_agency.controller.command.impl.page;

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
import java.util.Set;

public class UserHomeCommand implements Command {
    private static Logger logger = LogManager.getLogger(UserHomeCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        TourService tourService = TourServiceImpl.getInstance();
        String page;
        try {
            List<Tour> hotTours = tourService.findAllHotTours();
            if (hotTours.size() > 0) {
                session.setAttribute(AttributeName.HOT_TOURS, hotTours);
            } else {
                session.setAttribute(AttributeName.HOT_TOURS_NOTHING, true);
            }
            Set<String> countries = tourService.findAvailableCountries();
            session.setAttribute(AttributeName.COUNTRIES, countries);
            page = PathManager.getProperty(PathManager.PAGE_USER_HOME);
            logger.info("User home page forward.");
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}