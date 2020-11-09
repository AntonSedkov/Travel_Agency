package by.epam.tagency.controller.command.impl;

import by.epam.tagency.controller.AttributeName;
import by.epam.tagency.controller.command.Command;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.ClientSheet;
import by.epam.tagency.model.entity.Tour;
import by.epam.tagency.model.entity.UserType;
import by.epam.tagency.model.service.SheetService;
import by.epam.tagency.model.service.TourService;
import by.epam.tagency.model.service.UserService;
import by.epam.tagency.model.service.impl.GeneralServiceImpl;
import by.epam.tagency.model.service.impl.SheetServiceImpl;
import by.epam.tagency.model.service.impl.TourServiceImpl;
import by.epam.tagency.model.service.impl.UserServiceImpl;
import by.epam.tagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
                        ClientSheet sheet = sheetService.findSheetByIdUser(idUser);
                        session.setAttribute(AttributeName.SHEET, sheet);
                        TourService tourService = TourServiceImpl.getInstance();
                        List<Tour> hotTours = tourService.findAllHotTours();
                        if (hotTours.size() > 0) {
                            session.setAttribute(AttributeName.HOT_TOURS, hotTours);
                        } else {
                            session.setAttribute(AttributeName.HOT_TOURS_NOTHING, true);
                        }
                        Set<String> countries = tourService.findAvailableCountries();
                        session.setAttribute(AttributeName.COUNTRIES, countries);
                        page = PathManager.getProperty(PathManager.PAGE_USER_HOME);
                        logger.info("Client log in successfully.");
                    }
                    case MODERATOR -> {
                        page = PathManager.getProperty(PathManager.PAGE_MODERATOR_HOME);
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