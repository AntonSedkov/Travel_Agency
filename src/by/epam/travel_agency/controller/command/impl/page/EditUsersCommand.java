package by.epam.travel_agency.controller.command.impl.page;

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

public class EditUsersCommand implements Command {
    private static Logger logger = LogManager.getLogger(EditUsersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService service = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        String page;
        try {
            String login = (String) session.getAttribute(AttributeName.USER);
            List<User> users = service.findAllUsersWithoutCurrent(login);
            session.setAttribute(AttributeName.USERS, users);
            page = PathManager.getProperty(PathManager.PAGE_ADMIN_EDIT_USERS);
            logger.info("Admin edit users page forward.");
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}