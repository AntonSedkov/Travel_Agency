package by.epam.travel_agency.model.entity;

import java.util.List;
import java.util.StringJoiner;

public class User extends Entity {

    private String login;
    private String email;
    private UserType role;
    private boolean status;
    private boolean approvedEmail;
    private List<ClientPassport> clientPassports;
    private String surname;
    private String name;
    private ClientSheet sheet;
    private List<ClientOrder> orders;

    private static final UserType DEFAULT_ROLE = UserType.USER;
    private static final Boolean DEFAULT_STATUS = true;


    public User() {
    }

    public User(long id, String login, String email, UserType role, boolean status) {
        super(id);
        this.login = login;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public User(String login, String email) {
        this.login = login;
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

    public List<ClientPassport> getClientPassports() {
        return clientPassports;
    }

    public void setClientPassports(List<ClientPassport> clientPassports) {
        this.clientPassports = clientPassports;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientSheet getSheet() {
        return sheet;
    }

    public void setSheet(ClientSheet sheet) {
        this.sheet = sheet;
    }

    public List<ClientOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<ClientOrder> orders) {
        this.orders = orders;
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
        if (role != user.role) return false;
        if (clientPassports != null ? !clientPassports.equals(user.clientPassports) : user.clientPassports != null)
            return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (sheet != null ? !sheet.equals(user.sheet) : user.sheet != null) return false;
        return orders != null ? orders.equals(user.orders) : user.orders == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (approvedEmail ? 1 : 0);
        result = 31 * result + (clientPassports != null ? clientPassports.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sheet != null ? sheet.hashCode() : 0);
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("login='" + login + "'")
                .add("email='" + email + "'")
                .add("role=" + role)
                .add("status=" + status)
                .add("approvedEmail=" + approvedEmail)
                .add("clientPassports=" + clientPassports)
                .add("surname='" + surname + "'")
                .add("name='" + name + "'")
                .add("sheet=" + sheet)
                .add("orders=" + orders)
                .toString();
    }

}