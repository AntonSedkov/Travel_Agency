package by.epam.travel_agency.util;

import java.util.ResourceBundle;

public class AlertManager {
    public static final String KEY_LOGIN_ERROR = "alert.loginerror";
    public static final String KEY_REGISTER_ERROR = "alert.registererror";
    public static final String KEY_NULL_PAGE = "alert.nullpage";
    public static final String KEY_WRONG_ACTION = "alert.wrongaction";
    public static final String KEY_SERVICE_ERROR = "alert.serviceerror";
    private static final String RESOURCE_PATH = "configuration.alerts";

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(RESOURCE_PATH);

    private AlertManager() {
    }

    public static String getProperty(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }

}