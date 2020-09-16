package by.epam.travel_agency.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    String execute(HttpServletRequest request);

    default void refresh( /*код*/) {
        // TODO: 30.08.2020 метод для возвращения на ту же страницу
    }

}