package by.epam.travel_agency.validator;

public class GeneralValidator {
    private static final String DIGITS_VALUE = "\\d+";
    private static final String LITERALS_VALUE = "[A-Za-z]+";
    private static final String DATE_FORMAT = "\\b[\\d]{4}-[\\d]{2}-[\\d]{2}\\b";

    private GeneralValidator() {
    }

    public static boolean isDigitValue(String value) {
        boolean result = false;
        if (value != null && !value.isBlank()) {
            result = value.strip().matches(DIGITS_VALUE);
        }
        return result;
    }

    public static boolean isLatinLiterals(String value) {
        boolean result = false;
        if (value != null && !value.isBlank()) {
            result = value.strip().matches(LITERALS_VALUE);
        }
        return result;
    }

    public static boolean isDateFormat(String value) {
        boolean result = false;
        if (value != null && !value.isBlank()) {
            result = value.strip().matches(DATE_FORMAT);
        }
        return result;
    }

}