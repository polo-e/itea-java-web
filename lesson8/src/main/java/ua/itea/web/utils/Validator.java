package ua.itea.web.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final String EMAIL_PATTERN = "^(.+)@(\\S+)$";

    // at least 1 digit, 1 lowercase, 1 uppercase & special character, mim 8 & max 20 characters
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    public static boolean isValidEmail(final String email) {
        Matcher matcher = Pattern.compile(EMAIL_PATTERN).matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        Matcher matcher = Pattern.compile(PASSWORD_PATTERN).matcher(password);
        return matcher.matches();
    }
}
