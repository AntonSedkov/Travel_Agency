package by.epam.travel_agency.validator;

import by.epam.travel_agency.model.entity.HotelType;
import by.epam.travel_agency.model.entity.TourType;
import by.epam.travel_agency.model.entity.TransportType;

public class TourValidator {
    private static final String DIGITS_VALUE_RESTRICTION = "\\d{1,7}";
    private static final String LITERALS_VALUE = "[A-Za-z]+";
    private static final String DATE_FORMAT = "\\b[\\d]{4}-[\\d]{2}-[\\d]{2}\\b";
    public static final String IMAGE_NAME_FORMAT = "\\b[A-Za-z\\d]+\\.[A-Za-z]{2,5}\\b";

    private TourValidator() {
    }

    public static boolean isValidTourType(String tourType) {
        boolean result = false;
        if (tourType != null && !tourType.isBlank()) {
            for (TourType type : TourType.values()) {
                if (tourType.strip().toUpperCase().equals(type.name())) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public static boolean isDigitValue(String value) {
        boolean result = false;
        if (value != null && !value.isBlank()) {
            result = value.strip().matches(DIGITS_VALUE_RESTRICTION);
        }
        return result;
    }

    public static boolean isLiterals(String value) {
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

    public static boolean isValidHotelType(String hotelType) {
        boolean result = false;
        if (hotelType != null && !hotelType.isBlank()) {
            for (HotelType type : HotelType.values()) {
                if (hotelType.strip().toUpperCase().equals(type.name())) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public static boolean isValidTransportType(String transportType) {
        boolean result = false;
        if (transportType != null && !transportType.isBlank()) {
            for (TransportType type : TransportType.values()) {
                if (transportType.strip().toUpperCase().equals(type.name())) {
                    result = true;
                }
            }
        }
        return result;
    }

    public static boolean isImageName(String value) {
        boolean result = false;
        if (value != null && !value.isBlank()) {
            result = value.strip().matches(IMAGE_NAME_FORMAT);
        }
        return result;
    }

}