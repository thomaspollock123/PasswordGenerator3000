package user_page;

import java.util.Random;

public class PassGen {

    public String GeneratePassword() {
        int a_letter = 97; // letter 'a'
        int z_letter = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);

        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = a_letter + (int) (random.nextFloat() * (z_letter - a_letter + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
