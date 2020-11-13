package by.epam.tagency.controller.command;

import by.epam.tagency.controller.command.impl.*;
import by.epam.tagency.controller.command.impl.page.*;
import by.epam.tagency.util.PathManager;

public enum CommandType {

    GUEST_IN(new GuestHomeCommand()),
    LOGIN_PAGE(request -> PathManager.getProperty(PathManager.PAGE_GUEST_AUTH)),
    REGISTER_PAGE(request -> PathManager.getProperty(PathManager.PAGE_GUEST_REG)),
    ABOUT_PAGE(request -> PathManager.getProperty(PathManager.PAGE_GUEST_ABOUT)),
    ALL_TOURS(new AllToursCommand()),
    TYPE_TOURS(new ToursByTypeCommand()),
    COUNTRY_TOURS(new ToursByCountryCommand()),
    HOT_TOURS(new ToursHotCommand()),
    LOGIN(new LoginCommand()),
    REGISTER(new RegisterCommand()),
    CHANGE_LANG(new ChangeLanguageCommand()),
    SEARCH_TOUR(new SearchTourCommand()),
    ACTIVATE_EMAIL(new ActivateEmailCommand()),
    USER_IN(new UserHomeCommand()),
    SHEET_PAGE(request -> PathManager.getProperty(PathManager.PAGE_USER_SHEET)),
    SEE_OPERATIONS(new SeeOperationsCommand()),
    ALL_PASSPORTS(new AllPassportsCommand()),
    ADD_PASSPORT_PAGE(new AddPassportPageCommand()),
    MAKE_ORDER_PAGE(new MakeOrderPageCommand()),
    ALL_ORDERS(new AllOrdersCommand()),
    ACTUAL_ORDERS(new ActualOrdersPageCommand()),
    LOGOUT(new LogoutCommand()),
    CHANGE_LOGIN(new ChangeLoginCommand()),
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    CHANGE_EMAIL(new ChangeEmailCommand()),
    ADD_SHEET_SUM(new AddSheetSumCommand()),
    MAKE_ORDER(new MakeOrderCommand()),
    CANCEL_ORDER(new CancelOrderCommand()),
    ADD_PASSPORT(new AddPassportCommand()),
    SEE_TRAVEL_DOCS(new SeeTravelDocsCommand()),
    CHANGE_STATE(new ChangeOrderStateCommand()),
    ADMIN_IN(new AdminHomeCommand()),
    EDIT_USERS(new EditUsersCommand()),
    ADD_STAFF(request -> PathManager.getProperty(PathManager.PAGE_ADMIN_ADD_STAFF)),
    ACTIVATE_USER(new ActivateUserCommand()),
    DEACTIVATE_USER(new DeactivateUserCommand()),
    MODERATOR_IN(request -> PathManager.getProperty(PathManager.PAGE_MODERATOR_HOME)),
    ADD_TOUR_PAGE(new AddTourPageCommand()),
    EDIT_TOURS(new EditToursCommand()),
    EDIT_ORDERS_PAGE(new EditOrdersPageCommand()),
    ADD_ORDER_DOCS_PAGE(new AddOrderDocsPageCommand()),
    ADD_ORDER_DOC(new AddOrderDocCommand()),
    ADD_TOUR(new AddTourCommand()),
    CLOSE_TOUR(new CloseTourCommand()),
    SET_HOT_TOUR(new SetHotTourCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}