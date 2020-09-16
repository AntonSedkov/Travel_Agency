package by.epam.travel_agency.controller.command.impl;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.service.UserService;
import by.epam.travel_agency.model.service.impl.UserServiceImpl;
import by.epam.travel_agency.util.AlertManager;
import by.epam.travel_agency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

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
                request.setAttribute(AttributeName.USER, login);
                page = PathManager.getProperty(PathManager.KEY_PAGE_MAIN);
                logger.info("Log in");
            } else {
                request.setAttribute(AttributeName.LOGIN_ERROR,
                        AlertManager.getProperty(AlertManager.KEY_LOGIN_ERROR));
                page = PathManager.getProperty(PathManager.KEY_PAGE_LOGIN);
            }
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.KEY_PAGE_INDEX);
        }
        return page;
    }

}