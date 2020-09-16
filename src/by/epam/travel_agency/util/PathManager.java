package by.epam.travel_agency.util;

import java.util.ResourceBundle;

public class PathManager {
    public static final String KEY_PAGE_INDEX = "path.page.index";
    public static final String KEY_PAGE_LOGIN = "path.page.login";
    public static final String KEY_PAGE_MAIN = "path.page.main";
    public static final String KEY_PAGE_REGISTER = "path.page.register";
    public static final String KEY_PAGE_ERROR = "path.page.error";
    private static final String RESOURCE_PATH = "configuration.paths";

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(RESOURCE_PATH);

    private PathManager() {
    }

    public static String getProperty(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }

}