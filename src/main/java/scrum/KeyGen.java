package scrum;

import java.util.Random;

/**
 * Created by Marek on 21.03.2019.
 */
public class KeyGen {

    public static String getKey() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder key = new StringBuilder();
        Random rnd = new Random();
        while (key.length() < 6) {
            int index = (int) (rnd.nextFloat() * chars.length());
            key.append(chars.charAt(index));
        }
        String keyStr = key.toString();
        return keyStr;
    }
}
