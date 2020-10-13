package by.epam.travel_agency.controller;

import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.controller.command.CommandType;
import by.epam.travel_agency.util.AlertManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ActionProvider {
    private static Logger logger = LogManager.getLogger(ActionProvider.class);
    private static final String PARAM_COMMAND = "command";

    public static Command defineCommand(HttpServletRequest request) {
        Command currentCommand = null;
        String action = request.getParameter(PARAM_COMMAND);
        try {
            if (action != null && !action.isBlank()) {
                CommandType currentType = CommandType.valueOf(action.toUpperCase());
                currentCommand = currentType.getCommand();
            }
        } catch (IllegalArgumentException e) {
            logger.error("Wrong command parameter: " + action, e);
            request.setAttribute(AttributeName.WRONG_ACTION,                                    // TODO: 13.10.2020 TO ERROR PAGE
                    action + AlertManager.getProperty(AlertManager.KEY_WRONG_ACTION));
        }
        return currentCommand;
    }

}