package by.epam.travel_agency.model.entity;

public enum DiscountType {
    FIRSTLVL(0),
    SECONDLVL(5),
    THERDLVL(7),
    FOURTHLVL(10),
    FIFTHLVL(15);

    private final int discount;

    DiscountType(int discount) {
        this.discount = discount;
    }

    public int getDiscountValue() {
        return discount;
    }

    public static DiscountType getDiscountTypeByValue(int value) {
        return switch (value) {
            case 5 -> SECONDLVL;
            case 7 -> THERDLVL;
            case 10 -> FOURTHLVL;
            case 15 -> FIFTHLVL;
            default -> FIRSTLVL;
        };
    }

}