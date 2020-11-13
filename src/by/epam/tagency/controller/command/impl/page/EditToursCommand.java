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
import java.util.List;
import java.util.Set;

public class EditToursCommand implements Command {
    private static Logger logger = LogManager.getLogger(EditToursCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        TourService tourService = TourServiceImpl.getInstance();
        HttpSession session = request.getSession();
        String page;
        try {
            Set<String> tourTypes = GeneralServiceImpl.getInstance().formTourTypes();
            session.setAttribute(AttributeName.TOUR_TYPES, tourTypes);
            List<Tour> tours = tourService.findAllTours();
            session.setAttribute(AttributeName.ALL_TOURS, tours);
            page = PathManager.getProperty(PathManager.PAGE_MODERATOR_EDIT_TOURS);
            logger.info("Moderator edit tours page reload.");
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}