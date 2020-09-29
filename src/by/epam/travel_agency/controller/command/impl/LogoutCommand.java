package by.epam.travel_agency.controller.command.impl;

import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    private static Logger logger = LogManager.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = PathManager.getProperty(PathManager.PAGE_INDEX);
        request.getSession().invalidate();
        logger.info("User log out.");
        return page;
    }

}