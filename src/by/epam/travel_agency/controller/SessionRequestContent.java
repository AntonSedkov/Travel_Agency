package by.epam.travel_agency.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

public class SessionRequestContent {                            // TODO: 25.10.2020
    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttributes;
    private boolean validSession;

    public SessionRequestContent(HttpServletRequest request) {
        validSession = request.isRequestedSessionIdValid();
        if (validSession) {
            requestAttributes = new HashMap<>();
            Enumeration<String> reqAttributeNames = request.getAttributeNames();
            Iterator<String> reqIterator = reqAttributeNames.asIterator();
            while (reqIterator.hasNext()) {
                String attributeName = reqIterator.next();
                requestAttributes.put(attributeName, request.getAttribute(attributeName));
            }
            requestParameters = new HashMap<>(request.getParameterMap());
            sessionAttributes = new HashMap<>();
            HttpSession session = request.getSession();
            Enumeration<String> sesAttributeNames = session.getAttributeNames();
            Iterator<String> sesIterator = sesAttributeNames.asIterator();
            while (sesIterator.hasNext()) {
                String attributeName = sesIterator.next();
                sessionAttributes.put(attributeName, session.getAttribute(attributeName));
            }
        }
    }

    public HashMap<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    public HashMap<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    public HashMap<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }

    public boolean isValidSession() {
        return validSession;
    }

    public void restore() {
     /*   HttpServletRequest restoredRequest = new HttpServletRequest();
        Iterator<String> reqIterator = requestAttributes.keySet().iterator();
        while (reqIterator.hasNext()){

        }


        if (validSession) {
            Iterator<String> reqIterator = requestAttributes.keySet().iterator();
            while (reqIterator.hasNext()) {
                String attributeName = reqIterator.next();
                request.setAttribute(attributeName, requestAttributes.get(attributeName));
            }
            // TODO: 26.08.2020  RequestParameters are immutable, aren't they?
            Iterator<String> sesIterator = sessionAttributes.keySet().iterator();
            while (sesIterator.hasNext()) {
                String attributeName = sesIterator.next();
                request.getSession().setAttribute(attributeName, sessionAttributes.get(attributeName));
            }
        }*/
    }

}