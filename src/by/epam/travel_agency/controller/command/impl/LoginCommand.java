package by.epam.travel_agency.controller.command.impl;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.entity.UserType;
import by.epam.travel_agency.model.service.UserService;
import by.epam.travel_agency.model.service.impl.UserServiceImpl;
import by.epam.travel_agency.util.AlertManager;
import by.epam.travel_agency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private static Logger logger = LogManager.getLogger(LoginCommand.class);
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {
        UserService service = UserServiceImpl.getInstance();
        String page;
        String login = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);
        try {
            if (service.checkLoginData(login, password)) {
                HttpSession session = request.getSession();
                session.setAttribute(AttributeName.USER, login);
                UserType role = service.findRoleByLogin(login);
                session.setAttribute(AttributeName.ROLE, role.toString());
                request.setAttribute("URL",request.getRequestURL().toString());
                request.setAttribute("URI", request.getRequestURI());
                switch (role) {
                    case USER:
                        page = PathManager.getProperty(PathManager.PAGE_USER_HOME);  // TODO: 29.09.2020
                        logger.info("Client log in successfully.");
                        break;
                    case MODERATOR:
                        page = PathManager.getProperty(PathManager.PAGE_MODERATOR_HOME);  // TODO: 29.09.2020
                        logger.info("Moderator log in successfully.");
                        break;
                    case ADMIN:
                        page = PathManager.getProperty(PathManager.PAGE_ADMIN_HOME);  // TODO: 29.09.2020
                        logger.info("Admin log in successfully.");
                        break;
                    default:
                        page = PathManager.getProperty(PathManager.PAGE_ERROR);
                        logger.error("Wrong user type from database.");
                }
            } else {
                request.setAttribute(AttributeName.LOGIN_ERROR,
                        AlertManager.getProperty(AlertManager.KEY_LOGIN_ERROR));
                page = PathManager.getProperty(PathManager.PAGE_GUEST_AUTH);
            }
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}