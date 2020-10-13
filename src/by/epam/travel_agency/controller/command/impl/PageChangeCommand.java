package by.epam.travel_agency.controller.command.impl;

import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class PageChangeCommand implements Command {
    private static Logger logger = LogManager.getLogger(PageChangeCommand.class);
    private static final String PARAM_TARGET_PAGE = "targetpage";
    private static final String DEFAULT_PAGE = "path.guest.home";

    @Override
    public String execute(HttpServletRequest request) {
        String pageProperty = request.getParameter(PARAM_TARGET_PAGE);
        String targetPage = (pageProperty != null) ? pageProperty : DEFAULT_PAGE;
        String page = PathManager.getProperty(targetPage);
        logger.info("Page has been changed successfully to " + page);
        return page;
    }

}