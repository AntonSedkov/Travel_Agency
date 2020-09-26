package by.epam.travel_agency.controller.command.impl;

import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class PageChangeCommand implements Command {
    private static Logger logger = LogManager.getLogger(PageChangeCommand.class);
    private static final String PARAM_TARGET_PAGE = "targetpage";

    @Override
    public String execute(HttpServletRequest request) {
        String pageProperty = request.getParameter(PARAM_TARGET_PAGE);
        String page = PathManager.getProperty(pageProperty);
        return page;
    }
}
