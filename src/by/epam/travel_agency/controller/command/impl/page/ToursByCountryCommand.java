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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ToursByCountryCommand implements Command {
    private static Logger logger = LogManager.getLogger(ToursByCountryCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        TourService service = TourServiceImpl.getInstance();
        String page;
        try {
            Set<String> countries = service.findAvailableCountries();
            session.setAttribute(AttributeName.COUNTRIES, countries);
            Map<String, List<Tour>> tours = new HashMap<>();
            for (String country : countries) {
                List<Tour> currentTypeCountries = service.findToursByCountry(country);
                tours.put(country, currentTypeCountries);
            }
            session.setAttribute(AttributeName.TOURS_BY_COUNTRIES, tours);
            page = PathManager.getProperty(PathManager.PAGE_GUEST_COUNTRY_TOURS);
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}