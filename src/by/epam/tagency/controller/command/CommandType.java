package by.epam.tagency.controller.command;

import by.epam.tagency.controller.command.impl.*;
import by.epam.tagency.controller.command.impl.page.*;
import by.epam.tagency.util.PathManager;

public enum CommandType {

    ABOUT_PAGE(request -> PathManager.getProperty(PathManager.PAGE_GUEST_ABOUT)),
    SHEET_PAGE(request -> PathManager.getProperty(PathManager.PAGE_USER_SHEET)),
    REGISTER_PAGE(request -> PathManager.getProperty(PathManager.PAGE_GUEST_REG)),

    GUEST_IN(new GuestHomeCommand()),
    ALL_TOURS(new AllToursCommand()),
    TYPE_TOURS(new ToursByTypeCommand()),
    COUNTRY_TOURS(new ToursByCountryCommand()),
    HOT_TOURS(new ToursHotCommand()),

    LOGIN_PAGE(request -> PathManager.getProperty(PathManager.PAGE_GUEST_AUTH)),

    ADMIN_IN(new AdminHomeCommand()),
    EDIT_USERS(new EditUsersCommand()),
    ACTIVATE_USER(new ActivateUserCommand()),
    DEACTIVATE_USER(new DeactivateUserCommand()),
    ADD_STAFF(new AddStaffCommand()),

    MODERATOR_IN(request -> PathManager.getProperty(PathManager.PAGE_MODERATOR_HOME)),
    ADD_TOUR_PAGE(new AddTourPageCommand()),
    EDIT_TOURS(new EditToursCommand()),
    EDIT_ORDERS_PAGE(new EditOrdersPageCommand()),
    ADD_ORDER_DOCS_PAGE(new AddOrderDocsPageCommand()),
    ADD_ORDER_DOC(new AddOrderDocCommand()),
    ADD_TOUR(new AddTourCommand()),
    CLOSE_TOUR(new CloseTourCommand()),
    CHANGE_STATE(new ChangeOrderStateCommand()),

    USER_IN(new UserHomeCommand()),
    SEE_OPERATIONS(new SeeOperationsCommand()),
    ALL_PASSPORTS(new AllPassportsCommand()),
    MAKE_ORDER_PAGE(new MakeOrderPageCommand()),
    ALL_ORDERS(new AllOrdersCommand()),
    ACTUAL_ORDERS(new ActualOrdersPageCommand()),
    ADD_PASSPORT_PAGE(new AddPassportPageCommand()),

    LOGOUT(new LogoutCommand()),
    CHANGE_LOGIN(new ChangeLoginCommand()),
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    CHANGE_EMAIL(new ChangeEmailCommand()),
    ADD_SHEET_SUM(new AddSheetSumCommand()),
    MAKE_ORDER(new MakeOrderCommand()),
    CANCEL_ORDER(new CancelOrderCommand()),
    ADD_PASSPORT(new AddPassportCommand()),
    SEE_TRAVEL_DOCS(new SeeTravelDocsCommand()),

    SET_HOT_TOUR(new SetHotTourCommand()),

    CHANGE_LANG(new ChangeLanguageCommand()),
    LOGIN(new LoginCommand()),
    REGISTER(new RegisterCommand()),
    SEARCH_TOUR(new SearchTourCommand()),
    ACTIVATE_EMAIL(new ActivateEmailCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}