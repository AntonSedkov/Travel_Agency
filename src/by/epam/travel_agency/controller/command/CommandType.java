package by.epam.travel_agency.controller.command;

import by.epam.travel_agency.controller.command.impl.*;
import by.epam.travel_agency.controller.command.impl.page.*;

public enum CommandType {
    GUEST_IN(new GuestHomeCommand()),
    USER_IN(new UserHomeCommand()),
    MODERATOR_IN(new ModeratorHomeCommand()),
    ADMIN_IN(new AdminHomeCommand()),
    CHANGE_PAGE(new StaticPageCommand()),
    CHANGE_LANG(new ChangeLanguageCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTER(new RegisterCommand()),
    ACTIVATE_EMAIL(new ActivateEmailCommand()),
    ADD_STAFF(new RegisterCommand()),
    ACTIVATE_USER(new ActivateUserCommand()),
    DEACTIVATE_USER(new DeactivateUserCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}