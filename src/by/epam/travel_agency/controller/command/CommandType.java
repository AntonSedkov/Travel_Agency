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

    ADMIN_IN(new AdminHomeCommand()),
    EDIT_USERS(new EditUsersCommand()),
    ACTIVATE_USER(new ActivateUserCommand()),
    DEACTIVATE_USER(new DeactivateUserCommand()),
    ADD_STAFF(new AddStaffCommand()),

    MODERATOR_IN(new ModeratorHomeCommand()),
    ADD_TOUR_PAGE(new AddTourPageCommand()),
    EDIT_TOURS(new EditToursCommand()),
    EDIT_ORDERS(new EditOrdersCommand()),
    ADD_ORDER_DOCUMENTS(new AddOrderDocsCommand()),
    ADD_TOUR(new AddTourCommand()),
    CLOSE_TOUR(new CloseTourCommand()),

    USER_IN(new UserHomeCommand()),
    SEE_OPERATIONS(new SeeOperationsCommand()),

    ALL_PASSPORTS(new AllPassportsCommand()),

    ALL_ORDERS(new AllOrdersCommand()),

    LOGOUT(new LogoutCommand()),
    CHANGE_LOGIN(new ChangeLoginCommand()),
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    CHANGE_EMAIL(new ChangeEmailCommand()),
    ADD_SHEET_SUM(new AddSheetSumCommand()),
    PAY_ORDER(new PayOrderCommand()),



    actual_orders(new TestTTT()),
    previous_orders(new TestTTT()),

    add_passport(new TestTTT()),
    edit_passports(new TestTTT()),
    confirm_passports(new TestTTT()),
    MAKE_ORDER(new MakeOrderCommand()),



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