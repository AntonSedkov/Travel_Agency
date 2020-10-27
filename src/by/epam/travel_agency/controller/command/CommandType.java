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
    all_orders(new TestTTT()),
    actual_orders(new TestTTT()),
    previous_orders(new TestTTT()),
    all_passports(new TestTTT()),
    add_passport(new TestTTT()),
    edit_passports(new TestTTT()),
    confirm_passports(new TestTTT()),
    sheet(new TestTTT()),
    pay_tour(new TestTTT()),
    add_sheet_sum(new TestTTT()),
    see_operations(new TestTTT()),

    LOGOUT(new LogoutCommand()),

    CHANGE_LANG(new ChangeLanguageCommand()),
    LOGIN(new LoginCommand()),
    REGISTER(new RegisterCommand()),

    SEARCH_TOUR(new SearchTourCommand()),
    MAKE_ORDER(new MakeOrderCommand()),

    ACTIVATE_EMAIL(new ActivateEmailCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}