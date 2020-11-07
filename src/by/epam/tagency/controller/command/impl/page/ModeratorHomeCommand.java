package by.epam.tagency.controller.command.impl.page;

import by.epam.tagency.controller.command.Command;
import by.epam.tagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ModeratorHomeCommand implements Command {
    private static Logger logger = LogManager.getLogger(ModeratorHomeCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = PathManager.getProperty(PathManager.PAGE_MODERATOR_HOME);
        logger.info("Moderator home page reload.");
        return page;
    }

}