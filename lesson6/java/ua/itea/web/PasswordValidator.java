package ua.itea.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    // at least 1 digit, 1 lowercase, 1 uppercase & special character, mim 8 & max 20 characters
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public boolean isValid(String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
