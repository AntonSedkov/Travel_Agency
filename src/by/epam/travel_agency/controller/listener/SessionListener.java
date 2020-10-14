package by.epam.travel_agency.controller.listener;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.util.PathManager;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    private static final String DEFAULT_USER = "Traveler";
    private static final String DEFAULT_ROLE = "guest";
    private static final String DEFAULT_LANGUAGE = "en_EN";

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.setAttribute(AttributeName.USER, DEFAULT_USER);
        session.setAttribute(AttributeName.ROLE, DEFAULT_ROLE);
        session.setAttribute(AttributeName.LANGUAGE, DEFAULT_LANGUAGE);
        session.setAttribute(AttributeName.CURRENT_PAGE, PathManager.PAGE_GUEST_HOME);
    }

}