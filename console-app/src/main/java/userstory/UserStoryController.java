package userstory;

import java.util.Scanner;

public class UserStoryController {

    private Scanner scanner = new Scanner(System.in);

    public UserStory updateTitle(UserStory userStory) {
        System.out.println("\u001B[47m" + "\u001B[30m" + "Please type new title" + "\u001B[0m");
        String title = scanner.nextLine();
        userStory.setTitle(title);
        return userStory;
    }

    public UserStory updateDescription(UserStory userStory) {
        System.out.println("\u001B[47m" + "\u001B[30m" + "Please type new description" + "\u001B[0m");
        String description = scanner.nextLine();
        userStory.setDescription(description);
        return userStory;
    }

    public UserStory updateStoryPoints(UserStory userStory) {
        System.out.println("\u001B[47m" + "\u001B[30m" + "Please estimate task" + "\u001B[0m");
        Integer storyPoints = scanner.nextInt();
        userStory.setStoryPoints(storyPoints);
        return userStory;
    }

}
