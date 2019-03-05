package user;

import user.model.User;
import user.model.Users;
import utils.FileUtils;

import java.util.Scanner;

public class UserController {

    private Scanner scanner = new Scanner(System.in);

    public void createUser() {

        Users users = FileUtils.readUsersJsonFile();

        System.out.println("Please type user full name");
        String fullName = scanner.nextLine();
        System.out.println("Please type user email");
        String email = scanner.nextLine();
        System.out.println("Please type user role");
        String role = scanner.nextLine();

        User user = new User(fullName, email, role);

        users.add(user);

        FileUtils.saveUsersToJsonFile(users);

    }

}
