package by.epam.tagency.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = {"/jsp/*"})

public class DirectPathFilter implements Filter {
    private static final String DIRECT_REDIRECT_COMMAND = "/controller/command=guest_in";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(DIRECT_REDIRECT_COMMAND);
        dispatcher.forward(request, response);
        chain.doFilter(request, response);
    }

}