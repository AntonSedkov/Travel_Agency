package by.epam.travel_agency.model.dao;

public class StatementSql {
    private StatementSql() {
    }

    public static final String SQL_FIND_USER_BY_LOGIN = "SELECT password, role, status FROM users WHERE login = (?)";
    public static final String SQL_CHECK_LOGIN_UNIQUE = "SELECT COUNT(login) FROM users WHERE login = (?)";
    public static final String SQL_CREATE_USER = "INSERT INTO users (login, password, email) VALUES (?, ?, ?)";
}
