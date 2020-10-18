package by.epam.travel_agency.controller.command.impl.page;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class UserHomeCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String currentPage = (String) request.getSession().getAttribute(AttributeName.CURRENT_PAGE);
        return currentPage;
    }

}