package by.epam.tagency.controller.command.impl.page;

import by.epam.tagency.controller.AttributeName;
import by.epam.tagency.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class EditOrdersPageCommand implements Command {
    private static Logger logger = LogManager.getLogger(EditOrdersPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("This is EditOrdersCommand");
        return (String) request.getSession().getAttribute(AttributeName.CURRENT_PAGE);
    }

}