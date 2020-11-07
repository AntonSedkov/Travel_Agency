package by.epam.tagency.validator;

import by.epam.tagency.model.entity.HotelType;
import by.epam.tagency.model.entity.TourType;
import by.epam.tagency.model.entity.TransportType;

public class TourValidator {
    private static final String DIGITS_VALUE_PARAM = "\\d{1,7}";
    private static final String DIGITS_VALUE_DAYS = "\\d{1,3}";
    private static final String DIGITS_VALUE_DISCOUNT = "\\d{1,2}";

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

    public static boolean isDigitParamValue(String value) {
        boolean result = false;
        if (value != null && !value.isBlank()) {
            result = value.strip().matches(DIGITS_VALUE_PARAM);
        }
        return result;
    }

    public static boolean isDaysValue(String value) {
        boolean result = false;
        if (value != null && !value.isBlank()) {
            result = value.strip().matches(DIGITS_VALUE_DAYS);
        }
        return result;
    }

    public static boolean isDiscountValue(String value) {
        boolean result = false;
        if (value != null && !value.isBlank()) {
            result = value.strip().matches(DIGITS_VALUE_DISCOUNT);
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

}