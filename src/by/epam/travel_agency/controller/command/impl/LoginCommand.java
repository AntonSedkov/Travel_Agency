package by.epam.travel_agency.controller.command.impl;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.entity.Tour;
import by.epam.travel_agency.model.entity.User;
import by.epam.travel_agency.model.entity.UserType;
import by.epam.travel_agency.model.service.TourService;
import by.epam.travel_agency.model.service.UserService;
import by.epam.travel_agency.model.service.impl.TourServiceImpl;
import by.epam.travel_agency.model.service.impl.UserServiceImpl;
import by.epam.travel_agency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

public class LoginCommand implements Command {
    private static Logger logger = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService service = UserServiceImpl.getInstance();
        String page;
        String login = request.getParameter(AttributeName.USER);
        String password = request.getParameter(AttributeName.PASSWORD);
        try {
            if (service.checkLoginData(login, password)) {
                HttpSession session = request.getSession();
                session.setAttribute(AttributeName.USER, login);
                UserType role = service.findRoleByLogin(login);
                session.setAttribute(AttributeName.ROLE, role.toString().toLowerCase());
                switch (role) {
                    case USER -> {
                        page = PathManager.getProperty(PathManager.PAGE_USER_HOME);  // TODO: 29.09.2020
                        logger.info("Client log in successfully.");
                    }
                    case MODERATOR -> {
                        TourService tourService = TourServiceImpl.getInstance();
                        Set<String> tourTypes = tourService.formTourTypes();
                        session.setAttribute(AttributeName.TOUR_TYPES, tourTypes);
                        List<Tour> tours = tourService.findAllTours();
                        session.setAttribute(AttributeName.TOURS, tours);
                        page = PathManager.getProperty(PathManager.PAGE_MODERATOR_HOME);  // TODO: 29.09.2020
                        logger.info("Moderator log in successfully.");
                    }
                    case ADMIN -> {
                        List<User> users = service.findAllUsersWithoutCurrent(login);
                        session.setAttribute(AttributeName.USERS, users);
                        page = PathManager.getProperty(PathManager.PAGE_ADMIN_HOME);  // TODO: 29.09.2020
                        logger.info("Admin log in successfully.");
                    }
                    default -> {
                        page = PathManager.getProperty(PathManager.PAGE_ERROR);
                        logger.error("Wrong user type from database.");
                    }
                }
            } else {
                request.setAttribute(AttributeName.LOGIN_ERROR, true);
                page = PathManager.getProperty(PathManager.PAGE_GUEST_AUTH);
            }
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}