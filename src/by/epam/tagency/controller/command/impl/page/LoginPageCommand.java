package by.epam.tagency.controller.command.impl.page;

import by.epam.tagency.controller.command.Command;
import by.epam.tagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LoginPageCommand implements Command {
    private static Logger logger = LogManager.getLogger(LoginPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = PathManager.getProperty(PathManager.PAGE_GUEST_AUTH);
        logger.info("Page has been changed successfully to " + page);
        return page;
    }

}