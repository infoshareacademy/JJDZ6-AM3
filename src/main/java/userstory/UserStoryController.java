package userstory;

import java.util.Scanner;

public class UserStoryController {

    private Scanner scanner = new Scanner(System.in);

    public UserStory updateTitle(UserStory userStory) {
        System.out.println("Please type new title");
        String title = scanner.nextLine();
        userStory.setTitle(title);
        return userStory;
    }

    public UserStory updateDescription(UserStory userStory) {
        System.out.println("Please type new description");
        String description = scanner.nextLine();
        userStory.setDescription(description);
        return userStory;
    }

    public UserStory updateStoryPoints(UserStory userStory) {
        System.out.println("Please estimate task");
        Integer storyPoints = scanner.nextInt();
        userStory.setStoryPoints(storyPoints);
        return userStory;
    }

}
