package by.epam.travel_agency.controller.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@WebFilter(filterName = "RouteFilter", urlPatterns = "/*")
public class RouteFilter {//implements Filter {
   /* private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;

        String url = request.getRequestURI();
        String queryString = request.getQueryString();

        String urlQuery = url + ((queryString != null) ? ("?" + queryString) : "");
        req.setAttribute("urlQuery", urlQuery);

        LOGGER.log(Level.DEBUG, "RouteFilter has worked. urlQuery: " + urlQuery);
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }*/
}
