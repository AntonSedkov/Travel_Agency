package by.epam.travel_agency.model.entity;

public enum DiscountType {
    FIRSTLVL("0"),
    SECONDLVL("5"),
    THERDLVL("7"),
    FOURTHLVL("10"),
    FIFTHLVL("15");

    private final String discount;

    DiscountType(String discount) {
        this.discount = discount;
    }

    public String getDiscount() {
        return discount;
    }

}