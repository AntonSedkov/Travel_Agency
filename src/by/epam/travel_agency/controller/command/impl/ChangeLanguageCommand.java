package by.epam.travel_agency.controller.command.impl;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {
    private static Logger logger = LogManager.getLogger(ChangeLanguageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String lang = request.getParameter(AttributeName.LANGUAGE);
        lang = (lang != null) ? lang : (String) session.getAttribute(AttributeName.LANGUAGE);
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        session.setAttribute(AttributeName.LANGUAGE, lang);
        logger.info("Language has been changed successfully to " + lang + " lang and forward to " + page);
        return page;
    }

}