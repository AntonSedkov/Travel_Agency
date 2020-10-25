package by.epam.travel_agency.controller.command.impl;

import by.epam.travel_agency.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class MakeOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String field1= "id_tour";
        String field2= "id_user(current)";
        return null;
    }
}
