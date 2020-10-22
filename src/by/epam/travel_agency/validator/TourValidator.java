package by.epam.travel_agency.validator;

import by.epam.travel_agency.model.entity.TourType;

public class TourValidator {
    private static final String DIGITS_VALUE_RESTRICTION = "\\d{1,7}";
    private static final String LITERALS_VALUE = "[A-Za-z]+";
    private static final String DATE_FORMAT = "\\b[\\d]{4}-[\\d]{2}-[\\d]{2}\\b";

    private TourValidator() {
    }

    public static boolean isValidTourType(String tourType) {
        boolean result = false;
        for (TourType type : TourType.values()) {
            if (tourType.toUpperCase().equals(type.name())) {
                result = true;
            }
        }
        return result;
    }

    public static boolean isDigitValue(String value) {
        boolean result = false;
        if (value != null && !value.isBlank()) {
            result = value.trim().matches(DIGITS_VALUE_RESTRICTION);
        }
        return result;
    }

    public static boolean isLiterals(String value) {
        boolean result = false;
        if (value != null && !value.isBlank()) {
            result = value.trim().matches(LITERALS_VALUE);
        }
        return result;
    }

    public static boolean isDateFormat(String value) {
        boolean result = false;
        if (value != null && !value.isBlank()) {
            result = value.matches(DATE_FORMAT);
        }
        return result;
    }

}
