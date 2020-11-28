package by.epam.tagency.validator;

import java.util.regex.Pattern;

public class PassportValidator {
    private static final Pattern PASSPORT_LITERALS_VALUE = Pattern.compile("[A-Za-z]{1,30}");
    private static final Pattern PASSPORT_NUMBER_FORMAT = Pattern.compile("^[A-Za-z]{2}[\\d]{7}$");

    private PassportValidator() {
    }

    public static boolean isPersonalString(String value) {
        boolean result = (value != null && !value.isBlank() && PASSPORT_LITERALS_VALUE.matcher(value.strip()).matches());
        return result;
    }

    public static boolean isPassportNumber(String value) {
        boolean result = (value != null && !value.isBlank() && PASSPORT_NUMBER_FORMAT.matcher(value.strip()).matches());
        return result;
    }

}