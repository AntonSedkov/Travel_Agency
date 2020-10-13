package by.epam.travel_agency.controller.command;

import by.epam.travel_agency.controller.command.impl.*;

public enum CommandType {
    CHANGE_PAGE(new PageChangeCommand()),
    CHANGE_LANG(new ChangeLanguageCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTER(new RegisterCommand()),
    ACTIVATE_EMAIL(new ActivateEmailCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}