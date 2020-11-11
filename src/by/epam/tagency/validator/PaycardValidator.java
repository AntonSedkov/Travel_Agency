package by.epam.tagency.validator;

public class PaycardValidator {
    private static final String PAYCARD_FORMAT = "[\\d]{7}";

    private PaycardValidator() {
    }

    public static boolean isPaycardValue(String value) {
        boolean result = (value != null && !value.isBlank() && value.strip().matches(PAYCARD_FORMAT));
        return result;
    }

}