package by.epam.travel_agency.controller.command;

import by.epam.travel_agency.controller.command.impl.*;
import by.epam.travel_agency.controller.command.impl.page.*;

public enum CommandType {

    GUEST_IN(new GuestHomeCommand()),
    ALL_TOURS(new AllToursCommand()),
    TYPE_TOURS(new ToursByTypeCommand()),
    COUNTRY_TOURS(new ToursByCountryCommand()),
    HOT_TOURS(new ToursHotCommand()),
    CHANGE_PAGE(new StaticPageCommand()),
    LOGIN_PAGE(new LoginPageCommand()),

    ADMIN_IN(new AdminHomeCommand()),
    EDIT_USERS(new EditUsersCommand()),
    ACTIVATE_USER(new ActivateUserCommand()),
    DEACTIVATE_USER(new DeactivateUserCommand()),
    ADD_STAFF(new AddStaffCommand()),

    MODERATOR_IN(new ModeratorHomeCommand()),
    ADD_TOUR_PAGE(new AddTourPageCommand()),
    EDIT_TOURS(new EditToursCommand()),
    EDIT_ORDERS_PAGE(new EditOrdersPageCommand()),             // TODO: 02.11.2020
    ADD_ORDER_DOCS_PAGE(new AddOrderDocsPageCommand()),         // TODO: 02.11.2020
    ADD_TOUR(new AddTourCommand()),
    CLOSE_TOUR(new CloseTourCommand()),

    USER_IN(new UserHomeCommand()),
    SEE_OPERATIONS(new SeeOperationsCommand()),
    ALL_PASSPORTS(new AllPassportsCommand()),
    MAKE_ORDER_PAGE(new MakeOrderPageCommand()),
    ALL_ORDERS(new AllOrdersCommand()),

    LOGOUT(new LogoutCommand()),
    CHANGE_LOGIN(new ChangeLoginCommand()),
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    CHANGE_EMAIL(new ChangeEmailCommand()),
    ADD_SHEET_SUM(new AddSheetSumCommand()),
    PAY_ORDER(new PayOrderCommand()),
    MAKE_ORDER(new MakeOrderCommand()),

    actual_orders(new ToDo()),
    add_passport(new ToDo()),

    see_travel_docs(new ToDo()),
    finish_order(new ToDo()),
    cancel_order(new ToDo()),
    pay_order(new ToDo()),

    set_hot_tour(new ToDo()),

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