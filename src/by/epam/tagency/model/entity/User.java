package by.epam.tagency.model.entity;

import java.util.StringJoiner;

public class User extends Entity {
    private String login;
    private String email;
    private UserType role;
    private boolean status;
    private boolean approvedEmail;

    private static final Boolean DEFAULT_STATUS = true;

    public User() {
    }

    public User(String login, String email, String role) {
        this.login = login;
        this.email = email;
        this.role = UserType.valueOf(role.toUpperCase());
        this.status = DEFAULT_STATUS;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public boolean isApprovedEmail() {
        return approvedEmail;
    }

    public void setApprovedEmail(boolean approvedEmail) {
        this.approvedEmail = approvedEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        if (status != user.status) return false;
        if (approvedEmail != user.approvedEmail) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (approvedEmail ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id='" + getId() + "'")
                .add("login='" + login + "'")
                .add("email='" + email + "'")
                .add("role=" + role)
                .add("status=" + status)
                .add("approvedEmail=" + approvedEmail)
                .toString();
    }

}