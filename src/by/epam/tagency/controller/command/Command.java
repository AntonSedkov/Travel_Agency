package by.epam.tagency.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Base interface for Command pattern
 *
 * @author Anton Sedkov
 * @version 1.0
 */
public interface Command {

    /**
     * Define target page to go
     *
     * @param request is request from user
     * @return target page
     * @see HttpServletRequest
     * @see by.epam.tagency.controller.ActionProvider
     */
    String execute(HttpServletRequest request);

}