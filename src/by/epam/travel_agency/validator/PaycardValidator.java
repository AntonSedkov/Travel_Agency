package by.epam.travel_agency.validator;

public class PaycardValidator {
    public static final String PAYCARD_FORMAT = "[\\d]{7}";

    private PaycardValidator() {
    }

    public static boolean isPaycardValue(String value) {
        boolean result = false;
        if (value != null && !value.isBlank()) {
            result = value.strip().matches(PAYCARD_FORMAT);
        }
        return result;
    }

}