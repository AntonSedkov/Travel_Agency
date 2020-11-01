package by.epam.travel_agency.controller.command.impl.page;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.entity.ClientPassport;
import by.epam.travel_agency.model.entity.Tour;
import by.epam.travel_agency.model.service.PassportService;
import by.epam.travel_agency.model.service.TourService;
import by.epam.travel_agency.model.service.impl.PassportServiceImpl;
import by.epam.travel_agency.model.service.impl.TourServiceImpl;
import by.epam.travel_agency.util.PathManager;
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
        String idConcreteTour = request.getParameter(AttributeName.ID_TOUR);    // TODO: 01.11.2020 How to transfer Object through requests
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
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}