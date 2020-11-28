package by.epam.tagency.validator;

import by.epam.tagency.model.entity.UserType;

import java.util.regex.Pattern;

public class UserValidator {
    /*login: unique, maximum 16 characters - minimum 6 characters (letters, digits, underscore)
    Latin + At least 1 lowercase letter + At least 1 uppercase letter*/
    private static final Pattern LOGIN_REGEXP = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])[\\w]{6,16}$");
    /*password: maximum 16 characters - minimum 6 characters (letters, digits, underscore),
    Latin + At least 1 lowercase letter + At least 1 uppercase letter + At least 1 digit*/
    private static final Pattern PASSWORD_REGEXP = Pattern.compile("^(?=.*?\\d)(?=.*?[A-Z])(?=.*?[a-z])[\\w]{6,16}$");
    /*Email: first part according to RFC 5322 - then
    @ - letters, digits, underscore, dash at least one - dot - letters, digits, underscore from 2 to 6 characters */
    private static final Pattern EMAIL_REGEXP = Pattern.compile("^[\\w-\\.\\+!#$%&’*+\\/=?`{|}~^]+@[\\w-]+\\.[\\w]{2,6}$");

    private UserValidator() {
    }

    public static boolean isValidLogin(String login) {
        boolean result = (login != null && !login.isBlank() && LOGIN_REGEXP.matcher(login.strip()).matches());
        return result;
    }

    public static boolean isValidPassword(String password) {
        boolean result = (password != null && !password.isBlank() && PASSWORD_REGEXP.matcher(password.strip()).matches());
        return result;
    }

    public static boolean isValidEmail(String email) {
        boolean result = (email != null && !email.isBlank() && EMAIL_REGEXP.matcher(email.strip()).matches());
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