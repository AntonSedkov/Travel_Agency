package by.epam.tagency.validator;

import by.epam.tagency.model.entity.UserType;

public class UserValidator {
    /*login: unique, maximum 16 characters - minimum 6 characters (letters, digits, underscore)
    Latin + At least 1 lowercase letter + At least 1 uppercase letter*/
    private static final String LOGIN_REGEXP = "^(?=.*?[A-Z])(?=.*?[a-z])[\\w]{6,16}$";
    /*password: maximum 16 characters - minimum 6 characters (letters, digits, underscore),
    Latin + At least 1 lowercase letter + At least 1 uppercase letter + At least 1 digit*/
    private static final String PASSWORD_REGEXP = "^(?=.*?\\d)(?=.*?[A-Z])(?=.*?[a-z])[\\w]{6,16}$";
    /*Email: first part according to RFC 5322 - then
    @ - letters, digits, underscore, dash at least one - dot - letters, digits, underscore from 2 to 6 characters */
    private static final String EMAIL_REGEXP = "^[\\w-\\.\\+!#$%&’*+\\/=?`{|}~^]+@[\\w-]+\\.[\\w]{2,6}$";

    private UserValidator() {
    }

    public static boolean isValidLogin(String login) {
        boolean result = (login != null && !login.isBlank() && login.strip().matches(LOGIN_REGEXP));
        return result;
    }

    public static boolean isValidPassword(String password) {
        boolean result = (password != null && !password.isBlank() && password.strip().matches(PASSWORD_REGEXP));
        return result;
    }

    public static boolean isValidEmail(String email) {
        boolean result = (email != null && !email.isBlank() && email.strip().matches(EMAIL_REGEXP));
        return result;
    }

    public static boolean isValidRole(String role) {
        boolean result = false;
        if (role != null && !role.isBlank()) {
            for (UserType type : UserType.values()) {
                if (role.strip().toUpperCase().equals(type.toString())) {
                    result = true;
                }
            }
        }
        return result;
    }

}