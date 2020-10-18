package by.epam.travel_agency.controller.command.impl;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.entity.User;
import by.epam.travel_agency.model.service.UserService;
import by.epam.travel_agency.model.service.impl.UserServiceImpl;
import by.epam.travel_agency.util.PathManager;
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
        String idUser = request.getParameter(AttributeName.ID_USER);
        try {
            if (service.deactivateUser(idUser)) {
                String currentUser = (String) session.getAttribute(AttributeName.USER);
                List<User> users = service.findAllUsersWithoutCurrent(currentUser);
                session.setAttribute(AttributeName.USERS, users);
                logger.info("Deactivate user: " + idUser);
            } else {
                request.setAttribute(AttributeName.DEACTIVATE_USER_ERROR, true);
                logger.warn("User" + idUser + " deactivation is failed");
            }
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}