package backlog;

import menu.Menu;
import userstory.UserStoryMenu;

public class BacklogMenu {

    private UserStoryMenu userStoryMenu = new UserStoryMenu();

    public void selectOption() {

        System.out.println();
        System.out.println("---------------------------------------------------------------------------");
        System.out.println();
        System.out.println("                                 BACKLOG MENU                              ");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("  Choose:");
        System.out.println("  1 - Update Task");
        System.out.println("  2 - Back to main menu");
        System.out.println("  3 - QUIT APP");

        int menu = Menu.enterKey();

        switch (menu) {
            case 1:
                userStoryMenu.updateTaskMenu();
                break;
            case 2:
                return;
            case 3:
                System.out.println();
                System.out.println("----------------------------------- END -----------------------------------");
                System.out.println();
                System.exit(0);
            default:
                System.out.println("Incorrect digit. Please try again.");
        }
        System.out.println();

        selectOption();
    }
}
