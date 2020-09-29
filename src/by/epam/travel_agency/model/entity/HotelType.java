package by.epam.travel_agency.model.entity;

public enum HotelType {
    HOSTEL("hostel"),

    THREESTAR("3"),

    FOURSTAR("4"),

    FIFESTAR("5");

    private final String category;

    HotelType(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

}