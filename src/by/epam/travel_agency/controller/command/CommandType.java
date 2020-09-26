package by.epam.travel_agency.controller.command;

import by.epam.travel_agency.controller.command.impl.LoginCommand;
import by.epam.travel_agency.controller.command.impl.LogoutCommand;
import by.epam.travel_agency.controller.command.impl.PageChangeCommand;
import by.epam.travel_agency.controller.command.impl.RegisterCommand;

public enum CommandType {
    CHANGE_PAGE(new PageChangeCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTER(new RegisterCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}