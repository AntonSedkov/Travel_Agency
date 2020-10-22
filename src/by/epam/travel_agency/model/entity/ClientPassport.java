package by.epam.travel_agency.model.entity;

import java.util.List;
import java.util.StringJoiner;

public class ClientPassport extends Entity {
    private String surname;
    private String name;
    private long birthDate;
    private String country;
    private GenderType gender;
    private String identityNumber;
    private String passportSeries;
    private int passportNumber;
    private long issueDate;
    private long expiryDate;
    private List<TourDocument> tourDocuments;

    public ClientPassport() {
        super();
    }

    public ClientPassport(String surname, String name, long birthDate, String country, GenderType gender, String identityNumber, String passportSeries, int passportNumber, long issueDate, long expiryDate) {
        super();
        this.surname = surname;
        this.name = name;
        this.birthDate = birthDate;
        this.country = country;
        this.gender = gender;
        this.identityNumber = identityNumber;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
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

    public long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(long birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public long getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(long issueDate) {
        this.issueDate = issueDate;
    }

    public long getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(long expiryDate) {
        this.expiryDate = expiryDate;
    }

    public List<TourDocument> getTourDocuments() {
        return tourDocuments;
    }

    public boolean add(TourDocument tourDocument) {
        return tourDocuments.add(tourDocument);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClientPassport that = (ClientPassport) o;
        if (birthDate != that.birthDate) return false;
        if (passportNumber != that.passportNumber) return false;
        if (issueDate != that.issueDate) return false;
        if (expiryDate != that.expiryDate) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (gender != that.gender) return false;
        if (identityNumber != null ? !identityNumber.equals(that.identityNumber) : that.identityNumber != null)
            return false;
        if (passportSeries != null ? !passportSeries.equals(that.passportSeries) : that.passportSeries != null)
            return false;
        return tourDocuments != null ? tourDocuments.equals(that.tourDocuments) : that.tourDocuments == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (birthDate ^ (birthDate >>> 32));
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (identityNumber != null ? identityNumber.hashCode() : 0);
        result = 31 * result + (passportSeries != null ? passportSeries.hashCode() : 0);
        result = 31 * result + passportNumber;
        result = 31 * result + (int) (issueDate ^ (issueDate >>> 32));
        result = 31 * result + (int) (expiryDate ^ (expiryDate >>> 32));
        result = 31 * result + (tourDocuments != null ? tourDocuments.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ClientPassport.class.getSimpleName() + "[", "]")
                .add("surname='" + surname + "'")
                .add("name='" + name + "'")
                .add("birthDate=" + birthDate)
                .add("country='" + country + "'")
                .add("gender=" + gender)
                .add("identityNumber='" + identityNumber + "'")
                .add("passportSeries='" + passportSeries + "'")
                .add("passportNumber=" + passportNumber)
                .add("issueDate=" + issueDate)
                .add("expiryDate=" + expiryDate)
                .add("tourDocuments=" + tourDocuments)
                .toString();
    }

}