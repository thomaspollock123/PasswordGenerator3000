package utility;

import java.util.Random;

public class PassGen {

    public String GeneratePassword(boolean useLetters, boolean useCapitals, boolean useNumbers, boolean useSpecials, int targetLength) {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        String capitals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String specials = "!@#$%^&*()-_=+[]{};:,.<>?/";

        String allowedChars = "";

        if (useLetters) allowedChars += letters;
        if (useCapitals) allowedChars += capitals;
        if (useNumbers) allowedChars += numbers;
        if (useSpecials) allowedChars += specials;
        if (allowedChars.isEmpty()) return "";

        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < targetLength; i++) {
            int index = random.nextInt(allowedChars.length());
            password.append(allowedChars.charAt(index));
        }
        return password.toString();
    }
}
