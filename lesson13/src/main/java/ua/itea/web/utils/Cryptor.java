package ua.itea.web.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cryptor {

    private static final String SALT = "salt_here";

    public static String encrypt(String text) throws NoSuchAlgorithmException {

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(StandardCharsets.UTF_8.encode(text + SALT));

        return String.format("%032x", new BigInteger(1, messageDigest.digest()));
    }
}
