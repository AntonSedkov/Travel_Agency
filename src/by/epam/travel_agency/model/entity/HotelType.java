package by.epam.travel_agency.model.entity;

public enum HotelType {
    HOSTEL("hostel"),

    THREE("three"),

    FOUR("four"),

    FIVE("five");

    private final String category;

    HotelType(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

}