import utils.Setup;

import java.io.IOException;

public class ScrumBoard {
    public static void main(String[] args) {
        try {
            Setup.init();
        } catch (IOException e) {
            System.out.println("Initialization failed: " + e.getMessage());
        }
        Menu.startMenu();
    }
}
