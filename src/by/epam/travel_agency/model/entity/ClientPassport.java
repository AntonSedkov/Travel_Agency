package by.epam.travel_agency.model.entity;

import java.time.LocalDate;
import java.util.StringJoiner;

public class ClientPassport extends Entity {
    private String surname;
    private String name;
    private LocalDate birthDate;
    private String passportNumber;
    private String passportImage;

    public ClientPassport() {
    }

    public ClientPassport(String surname, String name, LocalDate birthDate, String passportNumber, String passportImage) {
        this.surname = surname;
        this.name = name;
        this.birthDate = birthDate;
        this.passportNumber = passportNumber;
        this.passportImage = passportImage;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportImage() {
        return passportImage;
    }

    public void setPassportImage(String passportImage) {
        this.passportImage = passportImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClientPassport that = (ClientPassport) o;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (birthDate != null ? !birthDate.equals(that.birthDate) : that.birthDate != null) return false;
        if (passportNumber != null ? !passportNumber.equals(that.passportNumber) : that.passportNumber != null)
            return false;
        return passportImage != null ? passportImage.equals(that.passportImage) : that.passportImage == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (passportNumber != null ? passportNumber.hashCode() : 0);
        result = 31 * result + (passportImage != null ? passportImage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ClientPassport.class.getSimpleName() + "[", "]")
                .add("id='" + getId() + "'")
                .add("surname='" + surname + "'")
                .add("name='" + name + "'")
                .add("birthDate=" + birthDate)
                .add("passportNumber='" + passportNumber + "'")
                .add("passportImage='" + passportImage + "'")
                .toString();
    }
}