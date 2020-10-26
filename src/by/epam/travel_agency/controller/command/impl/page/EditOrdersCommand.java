package by.epam.travel_agency.controller.command.impl.page;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class EditOrdersCommand implements Command {
    private static Logger logger = LogManager.getLogger(EditOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("This is EditOrdersCommand");
        return (String) request.getSession().getAttribute(AttributeName.CURRENT_PAGE);
    }

}