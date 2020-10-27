package by.epam.travel_agency.model.entity;

public enum TransportType {
    BUS("Bus"),
    AIRPLANE("Airplane");

    private final String value;

    TransportType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}