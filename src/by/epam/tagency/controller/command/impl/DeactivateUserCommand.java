package by.epam.tagency.controller.command.impl;

import by.epam.tagency.controller.AttributeName;
import by.epam.tagency.controller.command.Command;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.User;
import by.epam.tagency.model.service.UserService;
import by.epam.tagency.model.service.impl.UserServiceImpl;
import by.epam.tagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DeactivateUserCommand implements Command {
    private static Logger logger = LogManager.getLogger(DeactivateUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService service = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        String idUserModerate = request.getParameter(AttributeName.ID_USER_MODERATE);
        try {
            if (service.deactivateUser(idUserModerate)) {
                String currentUser = (String) session.getAttribute(AttributeName.USER);
                List<User> users = service.findAllUsersWithoutCurrent(currentUser);
                session.setAttribute(AttributeName.USERS, users);
                logger.info("Deactivate user: " + idUserModerate);
            } else {
                request.setAttribute(AttributeName.DEACTIVATE_USER_ERROR, true);
                logger.warn("User" + idUserModerate + " deactivation is failed");
            }
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}