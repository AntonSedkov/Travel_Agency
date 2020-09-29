package by.epam.travel_agency.model.dao;

public class StatementSql {
    private StatementSql() {
    }

    public static final String FIND_STATUS_BY_LOGIN = "SELECT status FROM users WHERE login = (?)";
    public static final String FIND_PASS_BY_LOGIN = "SELECT password FROM users WHERE login = (?)";
    public static final String FIND_ROLE_BY_LOGIN = "SELECT role FROM users WHERE login = (?)";
    public static final String CHECK_LOGIN_UNIQUE = "SELECT COUNT(login) FROM users WHERE login = (?)";
    public static final String CREATE_USER = "INSERT INTO users (login, password, email) VALUES (?, ?, ?)";
}
