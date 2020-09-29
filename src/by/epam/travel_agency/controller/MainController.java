package by.epam.travel_agency.controller;

import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.util.AlertManager;
import by.epam.travel_agency.util.PathManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/controller/*"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String page;
        Command command = ActionProvider.defineCommand(request);
        page = command.execute(request);
        if (page != null && !page.isBlank()) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = PathManager.getProperty(PathManager.PAGE_INDEX);
            request.getSession().setAttribute(AttributeName.NULL_PAGE,
                    AlertManager.getProperty(AlertManager.KEY_NULL_PAGE));
            response.sendRedirect(request.getContextPath() + page);
        }
    }

}