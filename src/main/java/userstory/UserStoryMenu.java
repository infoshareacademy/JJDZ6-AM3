package userstory;

import backlog.BacklogController;
import menu.Menu;
import utils.FileUtils;

import java.nio.file.Paths;
import java.util.Scanner;

public class UserStoryMenu {

    private static final String DIRECTORY = System.getProperty("user.dir");
    private static final String BACKLOG_JSON_FILE = Paths.get(DIRECTORY + "/Backlog.json").toString();

    private UserStoryController userStoryController = new UserStoryController();
    private BacklogController backlogController = new BacklogController();
    private Scanner scanner = new Scanner(System.in);

    public void updateTaskMenu() {
        System.out.println("Please type id of task you want to update");
        Integer id = scanner.nextInt();
        selectOption(id, backlogController.findById(id));
    }

    public void selectOption(Integer id, UserStory userStory) {

        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("                                     USER STORY MENU                               ");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("  Choose:");
        System.out.println("  1 - Update title");
        System.out.println("  2 - Update description");
        System.out.println("  3 - Estimate task");
        System.out.println("  4 - QUIT APP");

        int menu = Menu.enterKey();

        switch (menu) {
            case 1:
                UserStory updatedUserStoryWithNewTitle = userStoryController.updateTitle(userStory);
                FileUtils.saveListToJsonFile(backlogController.updateCollection(id, updatedUserStoryWithNewTitle), BACKLOG_JSON_FILE);
                break;
            case 2:
                UserStory updatedUserStoryWithNewDescription = userStoryController.updateDescription(userStory);
                FileUtils.saveListToJsonFile(backlogController.updateCollection(id, updatedUserStoryWithNewDescription), BACKLOG_JSON_FILE);
                break;
            case 3:
                UserStory updatedUserStoryWithNewEstimation = userStoryController.updateStoryPoints(userStory);
                FileUtils.saveListToJsonFile(backlogController.updateCollection(id, updatedUserStoryWithNewEstimation), BACKLOG_JSON_FILE);
                break;
            case 4:
                System.out.println();
                System.out.println("--------------------------------------- END ---------------------------------------");
                System.out.println();
                System.exit(0);
            default:
                System.out.println("Incorrect digit. Please try again.");
        }
        System.out.println();
        System.out.println("Press Enter to continue...");
        Menu.inRead();

        selectOption(id, userStory);
    }
}

