package by.epam.travel_agency.controller.command.impl;

import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ChangeLanguageCommand implements Command {
    private static Logger logger = LogManager.getLogger(ChangeLanguageCommand.class);
    private static final String PARAM_LANGUAGE = "language";
    private static final String DEFAULT_LANGUAGE = "en";

    @Override
    public String execute(HttpServletRequest request) {
        String langParam = request.getParameter(PARAM_LANGUAGE);
        String lang = (langParam != null) ? langParam : DEFAULT_LANGUAGE;
        request.getSession().setAttribute(PARAM_LANGUAGE, lang);
        String page = request.getRequestURI();                          // TODO: 13.10.2020  previous page
        logger.info("Language has been changed successfully to " + lang + " lang and to " + page + " page.");
        return page;
    }

}