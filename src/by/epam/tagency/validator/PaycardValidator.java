package by.epam.tagency.validator;

import java.util.regex.Pattern;

public class PaycardValidator {
    private static final Pattern PAYCARD_FORMAT = Pattern.compile("[\\d]{7}");

    private PaycardValidator() {
    }

    public static boolean isPaycardValue(String value) {
        boolean result = (value != null && !value.isBlank() && PAYCARD_FORMAT.matcher(value.strip()).matches());
        return result;
    }

}