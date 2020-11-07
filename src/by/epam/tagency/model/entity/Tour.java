package by.epam.tagency.model.entity;

import java.time.LocalDate;
import java.util.StringJoiner;

public class Tour extends Entity {

    private TourType tourType;
    private String country;
    private String hotelName;
    private HotelType hotelType;
    private TransportType transport;
    private LocalDate startDate;
    private int days;
    private int price;
    private int availableQuantity;
    private String description;
    private String imagePath;
    private int discount;

    public Tour() {
    }

    public Tour(TourType tourType, String country, String hotelName, HotelType hotelType, TransportType transport, LocalDate startDate, int days, int price, int availableQuantity, String description, String imagePath, int discount) {
        this.tourType = tourType;
        this.country = country;
        this.hotelName = hotelName;
        this.hotelType = hotelType;
        this.transport = transport;
        this.startDate = startDate;
        this.days = days;
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.description = description;
        this.imagePath = imagePath;
        this.discount = discount;
    }

    public TourType getTourType() {
        return tourType;
    }

    public void setTourType(TourType tourType) {
        this.tourType = tourType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public HotelType getHotelType() {
        return hotelType;
    }

    public void setHotelType(HotelType hotelType) {
        this.hotelType = hotelType;
    }

    public TransportType getTransport() {
        return transport;
    }

    public void setTransport(TransportType transport) {
        this.transport = transport;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Tour tour = (Tour) o;

        if (days != tour.days) return false;
        if (price != tour.price) return false;
        if (availableQuantity != tour.availableQuantity) return false;
        if (discount != tour.discount) return false;
        if (tourType != tour.tourType) return false;
        if (country != null ? !country.equals(tour.country) : tour.country != null) return false;
        if (hotelName != null ? !hotelName.equals(tour.hotelName) : tour.hotelName != null) return false;
        if (hotelType != tour.hotelType) return false;
        if (transport != tour.transport) return false;
        if (startDate != null ? !startDate.equals(tour.startDate) : tour.startDate != null) return false;
        if (description != null ? !description.equals(tour.description) : tour.description != null) return false;
        return imagePath != null ? imagePath.equals(tour.imagePath) : tour.imagePath == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (tourType != null ? tourType.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (hotelName != null ? hotelName.hashCode() : 0);
        result = 31 * result + (hotelType != null ? hotelType.hashCode() : 0);
        result = 31 * result + (transport != null ? transport.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + days;
        result = 31 * result + price;
        result = 31 * result + availableQuantity;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + discount;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Tour.class.getSimpleName() + "[", "]")
                .add("tourType=" + tourType)
                .add("country='" + country + "'")
                .add("hotelName='" + hotelName + "'")
                .add("hotelType=" + hotelType)
                .add("transport=" + transport)
                .add("startDate=" + startDate)
                .add("days=" + days)
                .add("price=" + price)
                .add("availableQuantity=" + availableQuantity)
                .add("description='" + description + "'")
                .add("imagePath='" + imagePath + "'")
                .add("discount=" + discount)
                .toString();
    }

}