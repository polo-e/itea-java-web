package ua.itea.web.utils;

public class Generator {

    public static String generateRandomChars() {

        char[] array = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int j = (int) (Math.random() * 26);
            char s = (j > 20) ? array[j] : Character.toUpperCase(array[j]);
            stringBuilder.append(s + " ");
        }
        return stringBuilder.toString();
    }
}
