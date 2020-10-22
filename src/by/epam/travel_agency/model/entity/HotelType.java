package by.epam.travel_agency.model.entity;

public enum HotelType {
    HOSTEL(0),

    THREE(3),

    FOUR(4),

    FIVE(5);

    private final int category;

    HotelType(int category) {
        this.category = category;
    }

    public int getCategory() {
        return category;
    }

}