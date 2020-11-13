package by.epam.tagency.controller.command.impl.page;

import by.epam.tagency.controller.AttributeName;
import by.epam.tagency.controller.command.Command;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.ClientPassport;
import by.epam.tagency.model.entity.Tour;
import by.epam.tagency.model.service.PassportService;
import by.epam.tagency.model.service.TourService;
import by.epam.tagency.model.service.impl.PassportServiceImpl;
import by.epam.tagency.model.service.impl.TourServiceImpl;
import by.epam.tagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

public class MakeOrderPageCommand implements Command {
    private static Logger logger = LogManager.getLogger(MakeOrderPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        TourService tourService = TourServiceImpl.getInstance();
        PassportService passportService = PassportServiceImpl.getInstance();
        String idConcreteTour = request.getParameter(AttributeName.ID_TOUR);
        boolean isConcreteTour = idConcreteTour != null;
        int idUser = (int) session.getAttribute(AttributeName.ID_USER);
        String page;
        try {
            Set<String> countries = tourService.findAvailableCountries();
            session.setAttribute(AttributeName.COUNTRIES, countries);
            if (isConcreteTour) {
                Tour tour = tourService.findTourById(idConcreteTour);
                request.setAttribute(AttributeName.CONCRETE_TOUR, tour);
            } else {
                List<Tour> tours = tourService.findAllTours();
                session.setAttribute(AttributeName.ALL_TOURS, tours);
            }
            List<ClientPassport> passports = passportService.findPassportsByIdUser(idUser);
            session.setAttribute(AttributeName.PASSPORTS, passports);
            page = PathManager.getProperty(PathManager.PAGE_USER_MAKE_ORDER);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}