package by.epam.tagency.validator;

import java.util.regex.Pattern;

public class GeneralValidator {
    private static final Pattern DIGITS_VALUE = Pattern.compile("\\d+");
    private static final Pattern LITERALS_VALUE = Pattern.compile("[A-Za-z]+");
    private static final Pattern DATE_FORMAT = Pattern.compile("\\b[\\d]{4}-[\\d]{2}-[\\d]{2}\\b");
    private static final Pattern IMAGE_NAME_FORMAT = Pattern.compile("\\b[A-Za-z\\d]+\\.[A-Za-z]{2,5}\\b");
    private static final Pattern DB_RESTRICTION_CHARACTERS_QUANTITY = Pattern.compile(".{3,64}");

    private GeneralValidator() {
    }

    public static boolean isDigitValue(String value) {
        boolean result = (value != null && !value.isBlank() && DIGITS_VALUE.matcher(value.strip()).matches());
        return result;
    }

    public static boolean isLatinLiterals(String value) {
        boolean result = (value != null && !value.isBlank() && LITERALS_VALUE.matcher(value.strip()).matches())
                && DB_RESTRICTION_CHARACTERS_QUANTITY.matcher(value.strip()).matches();
        return result;
    }

    public static boolean isDateFormat(String value) {
        boolean result = (value != null && !value.isBlank() && DATE_FORMAT.matcher(value.strip()).matches());
        return result;
    }

    public static boolean isImageName(String value) {
        boolean result = (value != null && !value.isBlank() && IMAGE_NAME_FORMAT.matcher(value.strip()).matches())
                && DB_RESTRICTION_CHARACTERS_QUANTITY.matcher(value.strip()).matches();
        return result;
    }

}