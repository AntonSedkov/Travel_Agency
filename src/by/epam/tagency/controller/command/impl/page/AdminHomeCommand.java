package by.epam.tagency.controller.command.impl.page;

import by.epam.tagency.controller.AttributeName;
import by.epam.tagency.controller.command.Command;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.service.UserService;
import by.epam.tagency.model.service.impl.GeneralServiceImpl;
import by.epam.tagency.model.service.impl.UserServiceImpl;
import by.epam.tagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

public class AdminHomeCommand implements Command {
    private static Logger logger = LogManager.getLogger(AdminHomeCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserService service = UserServiceImpl.getInstance();
        String page;
        try {
            Map<String, Integer> usersByRoles = service.countUsersQuantityByRole();
            int quantityUsers = GeneralServiceImpl.getInstance().sumListValues(new ArrayList<>(usersByRoles.values()));
            session.setAttribute(AttributeName.USERS_BY_ROLES, usersByRoles);
            session.setAttribute(AttributeName.QUANTITY_USERS, quantityUsers);
            page = PathManager.getProperty(PathManager.PAGE_ADMIN_HOME);
            logger.info("Admin home page created.");
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}