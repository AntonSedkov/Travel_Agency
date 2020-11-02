package by.epam.travel_agency.model.dao;

public class StatementSql {
    private StatementSql() {
    }

    public static final String FIND_STATUS_BY_LOGIN = "SELECT status FROM users WHERE login = (?)";
    public static final String FIND_PASS_BY_LOGIN = "SELECT password FROM users WHERE login = (?)";
    public static final String FIND_ROLE_BY_LOGIN = "SELECT role FROM users WHERE login = (?)";
    public static final String CHECK_LOGIN_UNIQUE = "SELECT COUNT(login) FROM users WHERE login = (?)";
    public static final String CREATE_USER = "INSERT INTO users (login, password, email, role) VALUES (?, ?, ?, ?)";
    public static final String DEACTIVATE_USER = "UPDATE users SET status = false WHERE id_user = (?)";
    public static final String ACTIVATE_USER = "UPDATE users SET status = true WHERE id_user = (?)";
    public static final String ACTIVATE_USER_EMAIL = "UPDATE users SET email_approved = true WHERE login = (?)";
    public static final String FIND_ALL_USERS_WITHOUT_CURRENT = "SELECT id_user, login, email, role, status, email_approved FROM users WHERE login != (?)";

    public static final String FIND_ALL_TOURS_ACTIVE = "SELECT id_tour, tour_purpose, country, hotel_name, hotel_stars, transport, date_start, " +
            "quantity_of_days, price, quantity_tours, description, image_path FROM tours WHERE quantity_tours >= 1";

    public static final String FIND_TOUR_BY_ID = "SELECT id_tour, tour_purpose, country, hotel_name, hotel_stars, transport, date_start, " +
            "quantity_of_days, price, quantity_tours, description, image_path FROM tours WHERE id_tour = (?)";

    public static final String FIND_TOUR_BY_PARAMETERS = "SELECT id_tour, tour_purpose, country, hotel_name, hotel_stars, transport, date_start, " +
            "quantity_of_days, price, quantity_tours, description, image_path FROM tours " +
            "WHERE tour_purpose = (?) AND country = (?) AND date_start >= (?) AND quantity_of_days >= (?) AND price <= (?) AND quantity_tours >= 1";
    public static final String FIND_TOUR_BY_COUNTRY = "SELECT id_tour, tour_purpose, country, hotel_name, hotel_stars, transport, date_start, " +
            "quantity_of_days, price, quantity_tours, description, image_path FROM tours WHERE country = (?) AND quantity_tours >= 1";
    public static final String FIND_TOUR_BY_PURPOSE = "SELECT id_tour, tour_purpose, country, hotel_name, hotel_stars, transport, date_start, " +
            "quantity_of_days, price, quantity_tours, description, image_path FROM tours WHERE tour_purpose = (?) AND quantity_tours >= 1";
    public static final String FIND_ALL_HOT_TOURS = "SELECT tours.id_tour, tours.tour_purpose, tours.country, tours.hotel_name," +
            "tours.hotel_stars, tours.transport, tours.date_start, tours.quantity_of_days, tours.price, tours.quantity_tours, " +
            "tours.description, tours.image_path, tours_hot.discount FROM tours INNER JOIN tours_hot ON tours.id_tour = tours_hot.id_tour_fk " +
            "WHERE tours.quantity_tours>0";
    public static final String SELECT_AVAILABLE_COUNTRIES = "SELECT DISTINCT country FROM tours";
    public static final String CREATE_TOUR = "INSERT INTO tours (tour_purpose, country, hotel_name, hotel_stars, transport, date_start, " +
            "quantity_of_days, price, quantity_tours, description, image_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String COUNT_USERS_BY_ROLE = "SELECT COUNT(*) FROM users WHERE role = (?)";
    public static final String CLOSE_TOUR = "UPDATE tours SET quantity_tours = 0 WHERE id_tour = (?)";

    public static final String CREATE_SHEET_WITH_ID_USER = "INSERT INTO sheet(id_user_fk) VALUES (?)";
    public static final String FIND_SHEET_BY_ID_USER = "SELECT id_sheet, sheet_sum, customer_discount FROM sheet WHERE id_user_fk = (?)";
    public static final String FIND_PAYCARD_BY_NUMBER = "SELECT id_paycard, card_sum, card_quantity FROM paycards WHERE card_quantity>1 AND card_number = (?)";
    public static final String CREATE_OPERATION = "INSERT INTO operation (id_sheet_fk, operation_sum, operation_purpose) VALUES (?,?,?)";
    public static final String FIND_OPERATIONS_BY_ID_SHEET = "SELECT id_operation, operation_sum, operation_purpose FROM operation WHERE id_sheet_fk = (?)";
    public static final String FIND_PASSPORTS_BY_ID_USER = "SELECT id_passport, surname, name, birth_date, passport_number, passport_image FROM passport WHERE id_user_fk = (?)";


    public static final String CHANGE_SHEET_SUM = "UPDATE sheet SET sheet_sum = (?) WHERE id_user_fk = (?)";
    public static final String CHANGE_SHEET_DISCOUNT = "UPDATE sheet SET customer_discount = (?) WHERE id_user_fk = (?)";


    public static final String FIND_ID_USER_BY_LOGIN = "SELECT id_user FROM users WHERE login = (?)";
    public static final String CHANGE_LOGIN = "UPDATE users SET login = (?) WHERE login = (?)";
    public static final String CHANGE_PASSWORD = "UPDATE users SET password = (?) WHERE login = (?)";
    public static final String CHANGE_EMAIL = "UPDATE users SET email = (?), email_approved = false WHERE login = (?)";


    public static final String CREATE_STAFF_PERSONAL_DATA = "INSERT INTO staff (id_user_fk, surname, name) VALUES (?,?,?)";
    public static final String CREATE_HOT_TOURS = "INSERT INTO tours_hot (id_tour_fk, discount) VALUES (?,?)";


    public static final String CREATE_ORDER = "INSERT INTO orders (id_tour_fk, id_passport_fk, id_travel_docs_fk, date_order) VALUES (?,?,?,?)";
    public static final String CREATE_EMPTY_TRAVEL_DOCS = "INSERT INTO travel_docs VALUES ()";
    public static final String FIND_TOUR_QUANTITY_BY_ID = "SELECT id_tour, quantity_tours from tours where id_tour = (?)";

    public static final String SELECT_ORDERS_INFO_BY_ID_USER =
            "SELECT t.id_tour,t.tour_purpose,t.country,t.date_start, t.quantity_of_days, t.price, " +
                    "p.id_passport, p.surname, p.name, td.id_travel_docs, " +
                    "o.id_order, o.date_order, o.state " +
                    "from orders AS o " +
                    "INNER JOIN tours AS t ON o.id_tour_fk = t.id_tour " +
                    "INNER JOIN passport AS p ON o.id_passport_fk = p.id_passport " +
                    "INNER JOIN travel_docs AS td ON o.id_travel_docs_fk = td.id_travel_docs " +
                    "WHERE p.id_user_fk = (?) ORDER BY o.date_order DESC";

    public static final String SELECT_ACTUAL_ORDERS_BY_ID_USER =
            "SELECT t.id_tour,t.tour_purpose,t.country,t.date_start, t.quantity_of_days, t.price, " +
                    "p.id_passport, p.surname, p.name, td.id_travel_docs, " +
                    "o.id_order, o.date_order, o.state " +
                    "from orders AS o " +
                    "INNER JOIN tours AS t ON o.id_tour_fk = t.id_tour " +
                    "INNER JOIN passport AS p ON o.id_passport_fk = p.id_passport " +
                    "INNER JOIN travel_docs AS td ON o.id_travel_docs_fk = td.id_travel_docs " +
                    "WHERE p.id_user_fk = (?) AND o.state != 'finished' ORDER BY o.date_order DESC";

    public static final String SELECT_ORDERS_BY_ID_USER_AND_STATE =
            "SELECT t.id_tour,t.tour_purpose,t.country,t.date_start, t.quantity_of_days, t.price, " +
                    "p.id_passport, p.surname, p.name, td.id_travel_docs, " +
                    "o.id_order, o.date_order, o.state " +
                    "from orders AS o " +
                    "INNER JOIN tours AS t ON o.id_tour_fk = t.id_tour " +
                    "INNER JOIN passport AS p ON o.id_passport_fk = p.id_passport " +
                    "INNER JOIN travel_docs AS td ON o.id_travel_docs_fk = td.id_travel_docs " +
                    "WHERE p.id_user_fk = (?) AND o.state = (?) ORDER BY o.date_order DESC";

    public static final String SELECT_ORDERS_BY_ID_USER_AND_ID_ORDER =
            "SELECT t.id_tour,t.tour_purpose,t.country,t.date_start, t.quantity_of_days, t.price, " +
                    "p.id_passport, p.surname, p.name, td.id_travel_docs, " +
                    "o.id_order, o.date_order, o.state " +
                    "from orders AS o " +
                    "INNER JOIN tours AS t ON o.id_tour_fk = t.id_tour " +
                    "INNER JOIN passport AS p ON o.id_passport_fk = p.id_passport " +
                    "INNER JOIN travel_docs AS td ON o.id_travel_docs_fk = td.id_travel_docs " +
                    "WHERE p.id_user_fk = (?) AND o.id_order = (?) ORDER BY o.date_order DESC";


}