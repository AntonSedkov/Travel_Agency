package by.epam.travel_agency.util;

import java.util.ResourceBundle;

public class AlertManager {
    public static final String KEY_NULL_PAGE = "alert.nullpage";
    public static final String KEY_WRONG_ACTION = "alert.wrongaction";
    private static final String RESOURCE_PATH = "configuration.alerts";

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(RESOURCE_PATH);

    private AlertManager() {
    }

    public static String getProperty(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }

}