package by.epam.tagency.util;

public class XssSafeUtil {
    private static final String XSS_REGEXP = "</?script>";
    private static final String EMPTY_STRING = "";

    private XssSafeUtil() {
    }

    public static String xssSafeString(String enterString) {
        return enterString.replaceAll(XSS_REGEXP, EMPTY_STRING);
    }

}