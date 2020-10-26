package by.epam.travel_agency.controller.command.impl.page;

import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ModeratorHomeCommand implements Command {
    private static Logger logger = LogManager.getLogger(ModeratorHomeCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        /*TourService tourService = TourServiceImpl.getInstance();
        HttpSession session = request.getSession();
        String page;
        try {
            Set<String> tourTypes = tourService.formTourTypes();
            session.setAttribute(AttributeName.TOUR_TYPES, tourTypes);
            List<Tour> tours = tourService.findAllTours();
            session.setAttribute(AttributeName.TOURS, tours);*/
        String page = PathManager.getProperty(PathManager.PAGE_MODERATOR_HOME);
        logger.info("Moderator home page reload.");
      /*  } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }*/
        return page;
    }

}