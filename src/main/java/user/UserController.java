package user;

import user.model.User;
import utils.FileUtils;

import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class UserController {

    private Scanner scanner = new Scanner(System.in);
    private static final String USERS_JSON_FILE = Paths.get("Users.json").toString();

    public void createUser() {

        List<User> users = FileUtils.readListFromJsonFile(USERS_JSON_FILE);

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

}
