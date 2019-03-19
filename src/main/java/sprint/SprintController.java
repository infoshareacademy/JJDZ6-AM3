package sprint;

import com.google.gson.reflect.TypeToken;
import user.model.User;
import utils.FileUtils;

import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class SprintController {

        private Scanner scanner = new Scanner(System.in);
        private static final String SPRINT_JSON_FILE = Paths.get("Sprint.json").toString();
        private static final Type collectionType = new TypeToken<List<Sprint>>() {
        }.getType();

        public void createSprint() {

            List<Sprint> sprints = FileUtils.readListFromJsonFile(SPRINT_JSON_FILE, collectionType);

            System.out.println("Please type Sprint name");
            String sprintName = scanner.nextLine();

            Sprint sprint = new Sprint(sprintName);

            sprints.add(sprint);

            FileUtils.saveListToJsonFile(sprints, SPRINT_JSON_FILE);

        }
}


/*
private Scanner scanner = new Scanner(System.in);
    private static final String USERS_JSON_FILE = Paths.get("Users.json").toString();
    private static final Type collectionType = new TypeToken<List<User>>() {
    }.getType();

    public void createUser() {

        List<User> users = FileUtils.readListFromJsonFile(USERS_JSON_FILE, collectionType);

        System.out.println("Please type user full name");
        String fullName = scanner.nextLine();
        System.out.println("Please type user email");
        String email = scanner.nextLine();
        System.out.println("Please type user role");
        String role = scanner.nextLine();

        User user = new User(fullName, email, role);

        users.add(user);

        FileUtils.saveListToJsonFile(users, USERS_JSON_FILE);

    }
 */