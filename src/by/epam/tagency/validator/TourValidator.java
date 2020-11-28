package by.epam.tagency.validator;

import by.epam.tagency.model.entity.HotelType;
import by.epam.tagency.model.entity.TourType;
import by.epam.tagency.model.entity.TransportType;

import java.util.regex.Pattern;

public class TourValidator {
    private static final Pattern DIGITS_VALUE_PARAM = Pattern.compile("\\d{1,7}");
    private static final Pattern DIGITS_VALUE_DAYS = Pattern.compile("\\d{1,3}");
    private static final Pattern DIGITS_VALUE_DISCOUNT = Pattern.compile("\\d{1,2}");

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
        boolean result = (value != null && !value.isBlank() && DIGITS_VALUE_PARAM.matcher(value.strip()).matches());
        return result;
    }

    public static boolean isDaysValue(String value) {
        boolean result = (value != null && !value.isBlank() && DIGITS_VALUE_DAYS.matcher(value.strip()).matches());
        return result;
    }

    public static boolean isDiscountValue(String value) {
        boolean result = (value != null && !value.isBlank() && DIGITS_VALUE_DISCOUNT.matcher(value.strip()).matches());
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
                    break;
                }
            }
        }
        return result;
    }

}