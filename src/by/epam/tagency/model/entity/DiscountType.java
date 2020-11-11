package by.epam.tagency.model.entity;

public enum DiscountType {
    FIRST_LVL(0),
    SECOND_LVL(5),
    THIRD_LVL(7),
    FOURTH_LVL(10),
    FIFTH_LVL(15);

    private final int value;

    DiscountType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DiscountType getDiscountTypeByValue(int value) {
        return switch (value) {
            case 5 -> SECOND_LVL;
            case 7 -> THIRD_LVL;
            case 10 -> FOURTH_LVL;
            case 15 -> FIFTH_LVL;
            default -> FIRST_LVL;
        };
    }

}