package by.epam.tagency.validator;

public class PassportValidator {
    private static final String PASSPORT_LITERALS_VALUE = "[A-Za-z]{1,30}";
    private static final String PASSPORT_NUMBER_FORMAT = "^[A-Za-z]{2}[\\d]{7}$";

    private PassportValidator() {
    }

    public static boolean isPersonalString(String value) {
        boolean result = (value != null && !value.isBlank() && value.strip().matches(PASSPORT_LITERALS_VALUE));
        return result;
    }

    public static boolean isPassportNumber(String value) {
        boolean result = (value != null && !value.isBlank() && value.strip().matches(PASSPORT_NUMBER_FORMAT));
        return result;
    }

}