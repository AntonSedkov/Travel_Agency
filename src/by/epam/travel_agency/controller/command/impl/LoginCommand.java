package by.epam.travel_agency.controller.command.impl;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.entity.ClientSheet;
import by.epam.travel_agency.model.entity.UserType;
import by.epam.travel_agency.model.service.SheetService;
import by.epam.travel_agency.model.service.UserService;
import by.epam.travel_agency.model.service.impl.GeneralServiceImpl;
import by.epam.travel_agency.model.service.impl.SheetServiceImpl;
import by.epam.travel_agency.model.service.impl.UserServiceImpl;
import by.epam.travel_agency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

public class LoginCommand implements Command {
    private static Logger logger = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService service = UserServiceImpl.getInstance();
        String page;
        String username = request.getParameter(AttributeName.USER);
        String password = request.getParameter(AttributeName.PASSWORD);
        try {
            if (service.checkLoginData(username, password)) {
                HttpSession session = request.getSession();
                session.setAttribute(AttributeName.USER, username);
                UserType role = service.findRoleByUsername(username);
                session.setAttribute(AttributeName.ROLE, role.toString().toLowerCase());
                int idUser = service.findIdUserByLogin(username);
                session.setAttribute(AttributeName.ID_USER, idUser);
                switch (role) {
                    case USER -> {
                        SheetService sheetService = SheetServiceImpl.getInstance();
                        ClientSheet sheet = sheetService.findSheetByIdUser(String.valueOf(idUser));
                        session.setAttribute(AttributeName.SHEET, sheet);
                        page = PathManager.getProperty(PathManager.PAGE_USER_HOME);  // TODO: 29.09.2020
                        logger.info("Client log in successfully.");
                    }
                    case MODERATOR -> {
                        /*TourService tourService = TourServiceImpl.getInstance();
                        Set<String> tourTypes = tourService.formTourTypes();
                        session.setAttribute(AttributeName.TOUR_TYPES, tourTypes);
                        List<Tour> tours = tourService.findAllTours();
                        session.setAttribute(AttributeName.TOURS, tours);*/
                        page = PathManager.getProperty(PathManager.PAGE_MODERATOR_HOME);  // TODO: 29.09.2020
                        logger.info("Moderator log in successfully.");
                    }
                    case ADMIN -> {
                        Map<String, Integer> usersByRoles = service.countUsersQuantityByRole();
                        int quantityUsers = GeneralServiceImpl.getInstance().sumListValues(new ArrayList<>(usersByRoles.values()));
                        session.setAttribute(AttributeName.USERS_BY_ROLES, usersByRoles);
                        session.setAttribute(AttributeName.QUANTITY_USERS, quantityUsers);
                        page = PathManager.getProperty(PathManager.PAGE_ADMIN_HOME);
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