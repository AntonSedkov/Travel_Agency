package by.epam.tagency.controller.command.impl.page;

import by.epam.tagency.controller.AttributeName;
import by.epam.tagency.controller.command.Command;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.Tour;
import by.epam.tagency.model.service.TourService;
import by.epam.tagency.model.service.impl.GeneralServiceImpl;
import by.epam.tagency.model.service.impl.TourServiceImpl;
import by.epam.tagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ToursByTypeCommand implements Command {
    private static Logger logger = LogManager.getLogger(ToursByTypeCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        TourService service = TourServiceImpl.getInstance();
        String page;
        try {
            Set<String> countries = service.findAvailableCountries();
            session.setAttribute(AttributeName.COUNTRIES, countries);
            Set<String> tourTypes = GeneralServiceImpl.getInstance().formTourTypes();
            session.setAttribute(AttributeName.TOUR_TYPES, tourTypes);
            Map<String, List<Tour>> tours = new HashMap<>();
            for (String type : tourTypes) {
                List<Tour> currentTypeTours = service.findToursByType(type);
                tours.put(type, currentTypeTours);
            }
            session.setAttribute(AttributeName.TOURS_BY_TYPES, tours);
            page = PathManager.getProperty(PathManager.PAGE_GUEST_TYPE_TOURS);
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}