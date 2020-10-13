package by.epam.travel_agency.controller.command.impl;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.service.UserService;
import by.epam.travel_agency.model.service.impl.UserServiceImpl;
import by.epam.travel_agency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ActivateEmailCommand implements Command {
    private static Logger logger = LogManager.getLogger(RegisterCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService service = UserServiceImpl.getInstance();
        String page;
        String user = request.getParameter(AttributeName.USER);
        try {
            if (service.activateUserEmail(user)) {
                page = PathManager.getProperty(PathManager.PAGE_GUEST_AUTH);
                request.setAttribute(AttributeName.ACTIVATE_EMAIL_SUCCESS, true);
                logger.info("Email activate for user: " + user);
            } else {
                page = PathManager.getProperty(PathManager.PAGE_GUEST_REG);
                request.setAttribute(AttributeName.ACTIVATE_EMAIL_ERROR, true);
                logger.warn("Email don't activate for user: " + user);
            }
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}