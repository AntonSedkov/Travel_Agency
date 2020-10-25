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

public class AllToursCommand implements Command {
    private static Logger logger = LogManager.getLogger(AllToursCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        TourService service = TourServiceImpl.getInstance();
        String page;
        try {
            Set<String> countries = service.findAvailableCountries();
            session.setAttribute(AttributeName.COUNTRIES, countries);
            List<Tour> tours = service.findAllTours();
            session.setAttribute(AttributeName.ALL_TOURS, tours);
            page = PathManager.getProperty(PathManager.PAGE_GUEST_ALL_TOURS);
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}