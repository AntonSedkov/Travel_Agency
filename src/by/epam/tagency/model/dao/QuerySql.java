package by.epam.tagency.model.dao;

public class QuerySql {

    // UserDao
    public static final String FIND_STATUS_BY_LOGIN = "SELECT status FROM users WHERE login = (?)";
    public static final String FIND_PASS_BY_LOGIN = "SELECT password FROM users WHERE login = (?)";
    public static final String FIND_ROLE_BY_LOGIN = "SELECT role FROM users WHERE login = (?)";
    public static final String CHECK_LOGIN_UNIQUE = "SELECT COUNT(login) FROM users WHERE login = (?)";
    public static final String CREATE_USER = "INSERT INTO users (login, password, email, role) VALUES (?, ?, ?, ?)";
    public static final String DEACTIVATE_USER = "UPDATE users SET status = false WHERE id_user = (?)";
    public static final String ACTIVATE_USER = "UPDATE users SET status = true WHERE id_user = (?)";
    public static final String ACTIVATE_USER_EMAIL = "UPDATE users SET email_approved = true WHERE login = (?)";
    public static final String FIND_ALL_USERS_WITHOUT_CURRENT = "SELECT id_user, login, email, role, status, email_approved FROM users WHERE login != (?)";
    public static final String COUNT_USERS_BY_ROLE = "SELECT COUNT(*) FROM users WHERE role = (?)";
    public static final String FIND_ID_USER_BY_LOGIN = "SELECT id_user FROM users WHERE login = (?)";
    public static final String CHANGE_LOGIN = "UPDATE users SET login = (?) WHERE login = (?)";
    public static final String CHANGE_PASSWORD = "UPDATE users SET password = (?) WHERE login = (?)";
    public static final String CHANGE_EMAIL = "UPDATE users SET email = (?), email_approved = false WHERE login = (?)";
    public static final String CREATE_SHEET_WITH_ID_USER = "INSERT INTO sheet(id_user_fk) VALUES (?)";

    //TourDao
    public static final String FIND_ALL_TOURS_ACTIVE = "SELECT id_tour, tour_purpose, country, hotel_name, hotel_stars, transport, date_start, " +
            "quantity_of_days, price, quantity_tours, description, image_path, discount FROM tours WHERE quantity_tours >= 1";
    public static final String FIND_TOUR_BY_ID = "SELECT id_tour, tour_purpose, country, hotel_name, hotel_stars, transport, date_start, " +
            "quantity_of_days, price, quantity_tours, description, image_path, discount FROM tours WHERE id_tour = (?)";
    public static final String FIND_TOUR_BY_PARAMETERS = "SELECT id_tour, tour_purpose, country, hotel_name, hotel_stars, transport, date_start, " +
            "quantity_of_days, price, quantity_tours, description, image_path, discount FROM tours " +
            "WHERE tour_purpose = (?) AND country = (?) AND date_start >= (?) AND quantity_of_days >= (?) AND price <= (?) AND quantity_tours >= 1";
    public static final String FIND_TOUR_BY_COUNTRY = "SELECT id_tour, tour_purpose, country, hotel_name, hotel_stars, transport, date_start, " +
            "quantity_of_days, price, quantity_tours, description, image_path, discount FROM tours WHERE country = (?) AND quantity_tours >= 1";
    public static final String FIND_TOUR_BY_PURPOSE = "SELECT id_tour, tour_purpose, country, hotel_name, hotel_stars, transport, date_start, " +
            "quantity_of_days, price, quantity_tours, description, image_path, discount FROM tours WHERE tour_purpose = (?) AND quantity_tours >= 1";
    public static final String FIND_ALL_HOT_TOURS = "SELECT id_tour, tour_purpose, country, hotel_name, hotel_stars, transport, date_start, " +
            "quantity_of_days, price, quantity_tours, description, image_path, discount FROM tours WHERE discount >0 AND quantity_tours>0";
    public static final String SELECT_AVAILABLE_COUNTRIES = "SELECT DISTINCT country FROM tours";
    public static final String CREATE_TOUR = "INSERT INTO tours (tour_purpose, country, hotel_name, hotel_stars, transport, date_start, " +
            "quantity_of_days, price, quantity_tours, description, image_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String CLOSE_TOUR = "UPDATE tours SET quantity_tours = 0 WHERE id_tour = (?)";
    public static final String SET_TOUR_HOT = "UPDATE tours SET discount = (?) WHERE id_tour = (?)";

    //SheetDao
    public static final String FIND_SHEET_BY_ID_USER = "SELECT id_sheet, sheet_sum, customer_discount FROM sheet WHERE id_user_fk = (?)";
    public static final String FIND_PAYCARD_BY_NUMBER = "SELECT id_paycard, card_sum, card_quantity FROM paycards WHERE card_quantity>1 AND card_number = (?)";
    public static final String CREATE_OPERATION = "INSERT INTO sheet_operation (id_sheet_fk, operation_sum, operation_purpose) VALUES (?,?,?)";
    public static final String CHANGE_SHEET_SUM = "UPDATE sheet SET sheet_sum = (?) WHERE id_user_fk = (?)";
    public static final String CHANGE_SHEET_DISCOUNT = "UPDATE sheet SET customer_discount = (?) WHERE id_user_fk = (?)";

    //OperationDao
    public static final String FIND_OPERATIONS_BY_ID_SHEET = "SELECT id_operation, operation_sum, operation_purpose FROM sheet_operation WHERE id_sheet_fk = (?)";

    // PassportDao
    public static final String FIND_PASSPORTS_BY_ID_USER = "SELECT id_passport, surname, name, birth_date, passport_number, passport_image FROM passport WHERE id_user_fk = (?)";
    public static final String CREATE_PASSPORT = "INSERT INTO passport (id_user_fk, surname, name, birth_date, passport_number, passport_image) VALUES (?,?,?,?,?,?)";

    // TravelDocsDao
    public static final String FIND_TRAVEL_DOCS_BY_ID = "SELECT voucher, insurance, ticket FROM travel_docs WHERE id_travel_docs = (?)";
    public static final String ADD_VOUCHER_TO_TRAVEL_DOCS = "UPDATE travel_docs SET voucher = (?) WHERE id_travel_docs = (?)";
    public static final String ADD_INSURANCE_TO_TRAVEL_DOCS = "UPDATE travel_docs SET insurance = (?) WHERE id_travel_docs = (?)";
    public static final String ADD_TICKET_TO_TRAVEL_DOCS = "UPDATE travel_docs SET ticket = (?) WHERE id_travel_docs = (?)";

    //OrderDao
    public static final String CREATE_ORDER = "INSERT INTO orders (id_tour_fk, id_passport_fk, id_travel_docs_fk, date_order) VALUES (?,?,?,?)";
    public static final String CREATE_EMPTY_TRAVEL_DOCS = "INSERT INTO travel_docs VALUES ()";
    public static final String FIND_TOUR_QUANTITY_BY_ID = "SELECT id_tour, quantity_tours FROM tours WHERE id_tour = (?)";
    public static final String FIND_ORDER_BY_ID = "SELECT id_order, id_travel_docs_fk FROM orders WHERE id_order = (?)";
    public static final String DELETE_TRAVEL_DOCS_BY_ID = "DELETE FROM travel_docs WHERE id_travel_docs = (?)";
    public static final String SET_ORDER_CONFIRMED = "UPDATE orders SET state = 'confirmed' WHERE id_order = (?)";
    public static final String SET_ORDER_ADDED_DOCS = "UPDATE orders SET state = 'added_docs' WHERE id_order = (?)";
    public static final String SET_ORDER_DECLINED = "UPDATE orders SET state = 'declined', comment = (?) WHERE id_order = (?)";


    public static final String SELECT_ORDERS_INFO_BY_ID_USER =
            "SELECT t.id_tour,t.tour_purpose,t.country,t.date_start, t.quantity_of_days, t.price, " +
                    "p.id_passport, p.surname, p.name, td.id_travel_docs, " +
                    "o.id_order, o.date_order, o.state, o.comment " +
                    "from orders AS o " +
                    "INNER JOIN tours AS t ON o.id_tour_fk = t.id_tour " +
                    "INNER JOIN passport AS p ON o.id_passport_fk = p.id_passport " +
                    "INNER JOIN travel_docs AS td ON o.id_travel_docs_fk = td.id_travel_docs " +
                    "WHERE p.id_user_fk = (?) ORDER BY o.date_order DESC";

    public static final String SELECT_ACTUAL_ORDERS_BY_ID_USER =
            "SELECT t.id_tour,t.tour_purpose,t.country,t.date_start, t.quantity_of_days, t.price, " +
                    "p.id_passport, p.surname, p.name, td.id_travel_docs, " +
                    "o.id_order, o.date_order, o.state, o.comment " +
                    "from orders AS o " +
                    "INNER JOIN tours AS t ON o.id_tour_fk = t.id_tour " +
                    "INNER JOIN passport AS p ON o.id_passport_fk = p.id_passport " +
                    "INNER JOIN travel_docs AS td ON o.id_travel_docs_fk = td.id_travel_docs " +
                    "WHERE p.id_user_fk = (?) AND o.state != 'finished' ORDER BY o.date_order DESC";

    public static final String FIND_ORDER_BY_ID_ORDER =
            "SELECT t.tour_purpose,t.country,t.date_start, t.quantity_of_days, t.price, " +
                    "td.id_travel_docs from orders AS o " +
                    "INNER JOIN tours AS t ON o.id_tour_fk = t.id_tour " +
                    "INNER JOIN travel_docs AS td ON o.id_travel_docs_fk = td.id_travel_docs " +
                    "WHERE o.id_order = (?)";

    public static final String SELECT_ORDERS_BY_ID_USER_AND_STATE =
            "SELECT t.id_tour,t.tour_purpose,t.country,t.date_start, t.quantity_of_days, t.price, " +
                    "p.id_passport, p.surname, p.name, td.id_travel_docs, " +
                    "o.id_order, o.date_order, o.state " +
                    "from orders AS o " +
                    "INNER JOIN tours AS t ON o.id_tour_fk = t.id_tour " +
                    "INNER JOIN passport AS p ON o.id_passport_fk = p.id_passport " +
                    "INNER JOIN travel_docs AS td ON o.id_travel_docs_fk = td.id_travel_docs " +
                    "WHERE p.id_user_fk = (?) AND o.state = (?) ORDER BY o.date_order DESC";

    public static final String FIND_ORDERS_WITH_USERS_TO_ADD_DOCS =
            "SELECT t.tour_purpose,t.country,t.date_start, t.quantity_of_days, t.price, " +
                    "p.surname, p.name, " +
                    "td.id_travel_docs, td.voucher, td.insurance, td.ticket, " +
                    "o.id_order, o.date_order, o.state, u.login " +
                    "from orders AS o " +
                    "INNER JOIN tours AS t ON o.id_tour_fk = t.id_tour " +
                    "INNER JOIN travel_docs AS td ON o.id_travel_docs_fk = td.id_travel_docs " +
                    "INNER JOIN passport AS p ON o.id_passport_fk = p.id_passport " +
                    "INNER JOIN users AS u ON p.id_user_fk = u.id_user " +
                    "WHERE o.state = 'paid' ORDER BY o.date_order DESC";

    public static final String FIND_ORDERS_WITH_USERS_TO_EDIT_ORDERS =
            "SELECT t.tour_purpose,t.country,t.date_start, t.quantity_of_days, t.price, " +
                    "p.surname, p.name, p.birth_date, p.passport_number, p.passport_image, " +
                    "td.id_travel_docs, " +
                    "o.id_order, o.date_order, o.state, u.login " +
                    "from orders AS o " +
                    "INNER JOIN tours AS t ON o.id_tour_fk = t.id_tour " +
                    "INNER JOIN travel_docs AS td ON o.id_travel_docs_fk = td.id_travel_docs " +
                    "INNER JOIN passport AS p ON o.id_passport_fk = p.id_passport " +
                    "INNER JOIN users AS u ON p.id_user_fk = u.id_user " +
                    "WHERE o.state = 'new' ORDER BY o.date_order DESC";

    private QuerySql() {
    }
}