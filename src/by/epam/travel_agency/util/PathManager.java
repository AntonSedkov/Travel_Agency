package by.epam.travel_agency.util;

import java.util.ResourceBundle;

public class PathManager {
    public static final String PAGE_INDEX = "path.page.index";

    public static final String PAGE_GUEST_HOME = "path.guest.home";
    public static final String PAGE_GUEST_AUTH = "path.guest.auth";
    public static final String PAGE_GUEST_REG = "path.guest.reg";
    public static final String PAGE_GUEST_ABOUT = "path.guest.about";
    public static final String PAGE_ADMIN_HOME = "path.admin.home";
    public static final String PAGE_MODERATOR_HOME = "path.moderator.home";
    public static final String PAGE_MODERATOR_TOUR_DATA = "path.moderator.tourdata";
    public static final String PAGE_MODERATOR_USER_DATA = "path.moderator.userdata";
    public static final String PAGE_USER_HOME = "path.user.home";
    public static final String PAGE_USER_PERSONAL_DATA = "path.user.personaldata";
    public static final String PAGE_USER_TOUR_DATA = "path.user.tourdata";
    public static final String PAGE_ERROR = "path.page.error";
    private static final String RESOURCE_PATH = "configuration.paths";

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(RESOURCE_PATH);

    private PathManager() {
    }

    public static String getProperty(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }

}