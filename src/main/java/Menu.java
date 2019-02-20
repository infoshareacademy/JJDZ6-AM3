import java.io.IOException;
import java.util.Scanner;

public class Menu {

    //wzorzec 'template'
    private Menu() {
        selectRole();
    }

    private void selectRole() {

        System.out.println();
        System.out.println("----------------------------------- Scrum Board -----------------------------------");
        System.out.println();
        System.out.println("                                     MAIN MENU                                     ");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("  Choose:");
        System.out.println("  1 - Administrator");
        System.out.println("  2 - Product Owner");
        System.out.println("  3 - QUIT APP");

        Scanner scanner = new Scanner(System.in);
        Integer menu = scanner.nextInt();

        switch (menu) {
            case 1:
                selectTaskForAdministrator();
                break;
            case 2:
                selectTaskForProductOwner();
                break;
            case 3:
                System.out.println();
                System.out.println("--------------------------------------- END ---------------------------------------");
                System.out.println();
                return;
            default:
                System.out.println("Please try again");
        }
    }

    private void selectTaskForAdministrator() {

        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("                                 ADMINISTRATOR MENU                                ");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("  Choose:");
        System.out.println("  1 - Create a new User");
        System.out.println("  2 - Create a new Project");
        System.out.println("  3 - Edit task in Backlog");
        System.out.println("  4 - Back to main menu");
        System.out.println("  5 - QUIT APP");

        Scanner scanner = new Scanner(System.in);
        Integer menu = scanner.nextInt();

        switch (menu) {
            case 1:
                System.out.println("JZ6AM-4 (Create a new User)");
                break;
            case 2:
                System.out.println("JZ6AM-5 (Create a new Project)");
                break;
            case 3:
                System.out.println("JZ6AM-7 (Edit task in Backlog)");
                break;
            case 4:
                selectRole();
                break;
            case 5:
                System.out.println();
                System.out.println("--------------------------------------- END ---------------------------------------");
                System.out.println();
                return;
            default:
                System.out.println("Please try again");
        }
        System.out.println();
        System.out.println("Press Enter to continue...");
        inRead();

        selectTaskForAdministrator();
    }
    private void selectTaskForProductOwner() {

        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("                                   PRODUCT OWNER                                   ");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("  Choose:");
        System.out.println("  1 - Load task into project backlog");
        System.out.println("  2 - Edit task in Backlog");
        System.out.println("  3 - Create a new Sprint");
        System.out.println("  4 - Back to main menu");
        System.out.println("  5 - QUIT APP");

        Scanner scanner = new Scanner(System.in);
        Integer menu = scanner.nextInt();

        switch (menu) {
            case 1:
                System.out.println("JZ6AM-6 (Load task into project backlog)");
                break;
            case 2:
                System.out.println("JZ6AM-7 (Edit task in Backlog)");
                break;
            case 3:
                System.out.println("JZ6AM-8 (Create a new Sprint)");
                break;
            case 4:
                selectRole();
                break;
            case 5:
                System.out.println();
                System.out.println("--------------------------------------- END ---------------------------------------");
                System.out.println();
                return;
            default:
                System.out.println("Please try again");
        }
        System.out.println();
        System.out.println("Press Enter to continue...");
        inRead();

        selectTaskForProductOwner();
    }

    private void inRead() {
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //metoda wytwórcza
    static public void startMenu() {
        new Menu();
    }
}
