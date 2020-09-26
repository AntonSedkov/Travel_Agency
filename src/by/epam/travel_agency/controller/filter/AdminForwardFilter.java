package by.epam.travel_agency.controller.filter;

import by.epam.travel_agency.model.entity.User;
import by.epam.travel_agency.model.entity.UserType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@WebFilter(dispatcherTypes = { DispatcherType.FORWARD }, urlPatterns = { "/jsp/admin/*" } )

public class AdminForwardFilter{// implements Filter {
    /*public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException  {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        User user = (User) httpRequest.getSession().getAttribute("user");
        if(user == null || !user.getRole().equals(UserType.ADMIN))  {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }
        chain.doFilter(request, response);
    }

    public void destroy()  {
    }*/
}