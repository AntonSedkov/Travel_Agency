package by.epam.tagency.controller.command.impl.page;

import by.epam.tagency.controller.AttributeName;
import by.epam.tagency.controller.command.Command;
import by.epam.tagency.model.service.impl.GeneralServiceImpl;
import by.epam.tagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

public class AddTourPageCommand implements Command {
    private static Logger logger = LogManager.getLogger(AddTourPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Set<String> tourTypes = GeneralServiceImpl.getInstance().formTourTypes();
        session.setAttribute(AttributeName.TOUR_TYPES, tourTypes);
        String page = PathManager.getProperty(PathManager.PAGE_MODERATOR_ADD_TOUR);
        logger.info("Moderator add tour page reload.");
        return page;
    }

}