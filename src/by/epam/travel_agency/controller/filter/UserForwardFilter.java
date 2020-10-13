package by.epam.travel_agency.controller.filter;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.model.entity.UserType;
import by.epam.travel_agency.util.PathManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/jsp/*"})

public class UserForwardFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String role = (String) httpRequest.getSession().getAttribute(AttributeName.ROLE);
        String page;
        switch (UserType.valueOf(role.toUpperCase())) {
            case USER -> page = PathManager.PAGE_USER_HOME;
            case MODERATOR -> page = PathManager.PAGE_MODERATOR_HOME;
            case ADMIN -> page = PathManager.PAGE_ADMIN_HOME;
            default -> page = PathManager.PAGE_GUEST_HOME;
        }
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
        chain.doFilter(request, response);
    }

}