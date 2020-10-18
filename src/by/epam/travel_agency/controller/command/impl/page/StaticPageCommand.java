package by.epam.travel_agency.controller.command.impl.page;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class StaticPageCommand implements Command {
    private static Logger logger = LogManager.getLogger(StaticPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String currentPage = (String) request.getSession().getAttribute(AttributeName.CURRENT_PAGE);
        String pageProperty = request.getParameter(AttributeName.TARGET_PAGE);
        String targetPage = (pageProperty != null) ? pageProperty : currentPage;
        String page = PathManager.getProperty(targetPage);
        logger.info("Page has been changed successfully to " + page);
        return page;
    }

}