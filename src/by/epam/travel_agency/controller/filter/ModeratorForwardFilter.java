package by.epam.travel_agency.controller.filter;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.model.entity.UserType;
import by.epam.travel_agency.util.PathManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/jsp/moderator/*"})

public class ModeratorForwardFilter implements Filter {
    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String role = (String) httpRequest.getSession().getAttribute(AttributeName.ROLE);
        if (role == null || !role.equals(UserType.MODERATOR.toString().toLowerCase())) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(PathManager.PAGE_INDEX);
            dispatcher.forward(request, response);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}