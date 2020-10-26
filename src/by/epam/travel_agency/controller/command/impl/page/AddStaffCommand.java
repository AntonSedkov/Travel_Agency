package by.epam.travel_agency.controller.command.impl.page;

import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddStaffCommand implements Command {
    private static Logger logger = LogManager.getLogger(AddStaffCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = PathManager.getProperty(PathManager.PAGE_ADMIN_ADD_STAFF);
        logger.info("Admin add staff page forward.");
        return page;
    }

}