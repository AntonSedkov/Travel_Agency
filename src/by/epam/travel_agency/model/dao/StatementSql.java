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

  //  public static final String CHANGE_LOGIN = "UPDATE users SET login = (?) WHERE id_user = (?)";
    public static final String CHANGE_PASSWORD = "UPDATE users SET password = (?) WHERE id_user = (?)";
    public static final String CHANGE_EMAIL = "UPDATE users SET email = (?), email_approved = false WHERE id_user = (?)";


    // Passport and Orders write)))








    public static final String CREATE_SHEET = "INSERT INTO customers_sheet(id_user_fk) VALUES (?)";
    public static final String CHANGE_SHEET_SUM = "UPDATE customers_sheet SET sheet_sum = (?) WHERE id_user_fk = (?)";
    public static final String CHANGE_SHEET_DISCOUNT = "UPDATE customers_sheet SET customer_discount = (?) WHERE id_user_fk = (?)";
    public static final String FIND_USER_SHEET = "SELECT id_customer_sheet, sheet_sum, customer_discount WHERE id_user_fk = (?)";


    public static final String SELECT_AVAILABLE_PURPOSE = "SELECT DISTINCT tour_purpose FROM tours";
    public static final String SELECT_AVAILABLE_COUNTRY = "SELECT DISTINCT country FROM tours";
    public static final String SELECT_AVAILABLE_HOTELS = "SELECT DISTINCT hotel_name, hotel_stars FROM tours";
    public static final String SELECT_AVAILABLE_TRANSPORT = "SELECT DISTINCT transport FROM tours";
    public static final String SELECT_MIN_PRICE = "SELECT MIN(price) FROM tours";
    public static final String SELECT_MAX_PRICE = "SELECT MAX(price) FROM tours";
    public static final String SELECT_MIN_DAYS = "SELECT MIN(quantity_of_days) FROM tours";
    public static final String SELECT_MAX_DAYS = "SELECT MAX(quantity_of_days) FROM tours";


    public static final String FIND_TOUR_BY_PURPOSE = "SELECT id_tour, country, hotel_name, hotel_stars, transport, date_start, " +
            "quantity_of_days,price, quantity_tours, description, image_path from tours where tour_purpose = (?)";
    public static final String FIND_TOUR_BY_COUNTRY = "SELECT id_tour, tour_purpose, hotel_name, hotel_stars, transport, date_start, " +
            "quantity_of_days, price, quantity_tours, description, image_path from tours where country = (?)";
    public static final String FIND_TOUR_BY_HOTEL_NAME = "SELECT id_tour, tour_purpose, country, hotel_name, hotel_stars, transport, date_start, " +
            "quantity_of_days, price, quantity_tours, description, image_path from tours where hotel_name = (?)";
    public static final String FIND_TOUR_BY_HOTEL_STARS_BIGGER_THAN = "SELECT id_tour, tour_purpose, country, hotel_name, transport, date_start, " +
            "quantity_of_days, price, quantity_tours, description, image_path from tours where  hotel_stars >= (?)";
    public static final String FIND_TOUR_BY_TRANSPORT = "SELECT id_tour, tour_purpose, country, hotel_name, hotel_stars, date_start, " +
            "quantity_of_days, price, quantity_tours, description, image_path from tours where  transport = (?)";
    public static final String FIND_TOUR_BY_DATE_BIGGER_THAN = "SELECT id_tour, tour_purpose, country, hotel_name, hotel_stars, transport, " +
            "quantity_of_days, price, quantity_tours, description, image_path from tours where date_start >= (?)";
    public static final String FIND_TOUR_BY_PRICE_LOWER_THAN = "SELECT id_tour, tour_purpose, country, hotel_name, hotel_stars, transport, date_start, " +
            "quantity_of_days, price, quantity_tours, description, image_path from tours where price <= (?)";
    public static final String FIND_TOUR_BY_DAYS_BIGGER_THAN = "SELECT id_tour, tour_purpose, country, hotel_name, hotel_stars, transport, date_start, " +
            "price, quantity_tours, description, image_path from tours where quantity_of_days >= (?)";
    public static final String FIND_TOUR_BY_QUANTITY_BIGGER_THAN = "SELECT id_tour, tour_purpose, country, hotel_name, hotel_stars, transport, date_start, " +
            "quantity_of_days, price, description, image_path from tours where quantity_tours >= (?)";
    public static final String FIND_TOUR_BY_PARAMETER = "SELECT id_tour, tour_purpose, country, hotel_name, hotel_stars, transport, date_start, " +
            "quantity_of_days, price, quantity_tours, description, image_path from tours where tour_purpose = (?) AND country = (?) AND " +
            "hotel_name = (?) AND hotel_stars >= (?) AND transport = (?) AND date_start >= (?) AND quantity_of_days >= (?) " +
            "AND price >= (?) AND quantity_tours >= (?)";

    public static final String FIND_ALL_TOUR_ACTIVE = "SELECT id_tour, tour_purpose, country, hotel_name, hotel_stars, transport, date_start, " +
            "quantity_of_days, price, quantity_tours, description, image_path from tours where quantity_tours >= 1";
    public static final String CREATE_TOUR = "INSERT INTO tours (tour_purpose, country, hotel_name, hotel_stars, transport, date_start, " +
            "quantity_of_days, price, description, image_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_QUANTITY = "UPDATE tours SET quantity_tours = (?) WHERE id_tour = (?)";
    public static final String FIND_TOUR_BY_ID = "SELECT tour_purpose, country, hotel_name, hotel_stars, transport, date_start," +
            "quantity_of_days, price, quantity_tours, description, image_path from tours where id_tour = (?)";


    public static final String FIND_ALL_HOT_TOURS_INFO = "SELECT tours.id_tour, tours.tour_purpose, tours.country, tours.hotel_name," +
            "tours.hotel_stars, tours.transport, tours.date_start, tours.quantity_of_days, tours.price, tours.quantity_of_days, " +
            "tours.description, tours.image_path, tours_hot.discount FROM tours INNER JOIN tours_hot ON tours.id_tour = tours_hot.id_tour_fk";

    public static final String CREATE_HOT_TOURS = "INSERT INTO tours_hot (id_tour_fk, discount) VALUES (?,?)";

    public static final String CREATE_MODERATOR = "INSERT INTO users (login, password, email, role, status) " +
            "VALUES (?,?,?,?,?)";
    public static final String CREATE_STAFF_DATA = "INSERT INTO staff (id_user_fk, surname, name) VALUES (?,?,?)";


}
