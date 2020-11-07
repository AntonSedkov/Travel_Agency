package by.epam.tagency.controller.command.impl;

import by.epam.tagency.controller.AttributeName;
import by.epam.tagency.controller.command.Command;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.service.UserService;
import by.epam.tagency.model.service.impl.UserServiceImpl;
import by.epam.tagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLoginCommand implements Command {
    private static Logger logger = LogManager.getLogger(ChangeLoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService service = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        String currentLogin = (String) session.getAttribute(AttributeName.USER);
        String newLogin = request.getParameter(AttributeName.NEW_USERNAME);
        try {
            if (service.changeUsername(currentLogin, newLogin)) {
                session.setAttribute(AttributeName.USER, newLogin);
                request.setAttribute(AttributeName.CHANGE_LOGIN, true);
                logger.info("Change login from " + currentLogin + " to " + newLogin);
            } else {
                request.setAttribute(AttributeName.CHANGE_LOGIN, false);
                logger.warn("Changing login is failed for " + currentLogin);
            }
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}