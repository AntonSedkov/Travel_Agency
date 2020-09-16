package by.epam.travel_agency.model.entity;

import java.util.StringJoiner;

public class User extends Entity {

    private String login;
    private String password;
    private String email;
    private UserType role;
    private boolean status;

    private static final UserType DEFAULT_ROLE = UserType.USER;
    private static final Boolean DEFAULT_STATUS = true;

    // TODO: 29.08.2020 Builder
    public User() {
    }

    public User(long id, String login, String password, String email, UserType role, boolean status) {
        super(id);
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = DEFAULT_ROLE;
        this.status = DEFAULT_STATUS;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getRole() {
        return role;
    }

    public void setRole(UserType role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        if (status != user.status) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id='" + getId() + "'")
                .add("login='" + login + "'")
                .add("password='" + password + "'")
                .add("email='" + email + "'")
                .add("role=" + role)
                .add("status=" + status)
                .toString();
    }
}