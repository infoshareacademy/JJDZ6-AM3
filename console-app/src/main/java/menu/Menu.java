package menu;

import backlog.BacklogController;
import backlog.BacklogMenu;
import scrum.ScrumProject;
import sprint.SprintController;
import user.UserController;
import utils.Styles;

import java.util.Scanner;

public class Menu {

    private UserController userController = new UserController();
    private BacklogController backlogController = new BacklogController();
    private BacklogMenu backlogMenu = new BacklogMenu();
    private SprintController sprintController = new SprintController();
    private ScrumProject scrumProject = new ScrumProject();


    //wzorzec 'template'
    private Menu() {
        selectRole();
    }


    private void selectRole() {

        System.out.println();
        System.out.println("------------------------------- Scrum Board -------------------------------");
        System.out.println(Styles.ACTION);
        System.out.println("                                 MAIN MENU                                 ");
        System.out.println(Styles.RESET);
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("  Choose:");
        System.out.println("  1 - Administrator");
        System.out.println("  2 - Product Owner");
        System.out.println("  3 - Show Backlog");
        System.out.println("  4 - QUIT APP");

        int menu = enterKey();

        switch (menu) {
            case 1:
                selectTaskForAdministrator();
                break;
            case 2:
                selectTaskForProductOwner();
                break;
            case 3:
                backlogController.showBacklogTasks();
                backlogMenu.selectOption();
                break;
            case 4:
                System.out.println(Styles.ACTION);
                System.out.println("----------------------------------- END -----------------------------------");
                System.out.println(Styles.RESET);
                System.exit(0);
            default:
                System.out.println("Incorrect digit. Please try again.");
        }
        System.out.println();

        selectRole();
    }

    private void selectTaskForAdministrator() {

        System.out.println("---------------------------------------------------------------------------");
        System.out.println("                             ADMINISTRATOR MENU                            ");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("  Choose:");
        System.out.println("  1 - Create a new User");
        System.out.println("  2 - Create a new Project");
        System.out.println("  3 - Back to main menu");
        System.out.println("  4 - QUIT APP");

        int menu = enterKey();

        switch (menu) {
            case 1:
                userController.createUser();
                break;
            case 2:
                scrumProject.createScrumProject();
                break;
            case 3:
                return;
            case 4:
                System.out.println();
                System.out.println("----------------------------------- END -----------------------------------");
                System.out.println();
                System.exit(0);
            default:
                System.out.println("Incorrect digit. Please try again.");
        }
        System.out.println();

        selectTaskForAdministrator();
    }

    private void selectTaskForProductOwner() {

        System.out.println("---------------------------------------------------------------------------");
        System.out.println("                               PRODUCT OWNER                               ");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("  Choose:");
        System.out.println("  1 - Load tasks into project backlog");
        System.out.println("  2 - Create a new Sprint");
        System.out.println("  3 - Back to main menu");
        System.out.println("  4 - QUIT APP");

        int menu = enterKey();

        switch (menu) {
            case 1:
                backlogController.importTasks();
                break;
            case 2:
                sprintController.createSprint();
                return;
            case 3:
                return;
            case 4:
                System.out.println();
                System.out.println("----------------------------------- END -----------------------------------");
                System.out.println();
                System.exit(0);
            default:
                System.out.println("Incorrect digit. Please try again.");
        }
        System.out.println();

        selectTaskForProductOwner();
    }

    public static int enterKey() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int menu = 0;
        try {
            menu = Integer.parseInt(input);
        } catch (NumberFormatException e) {
        }
        return menu;
    }

    //metoda wytwórcza
    public static void startMenu() {
        new Menu();
    }
}
