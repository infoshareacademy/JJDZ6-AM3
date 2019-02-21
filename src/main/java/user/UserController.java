package user;

import java.util.Scanner;

public class UserController {

    private Scanner scanner = new Scanner(System.in);
    private UserService userService = new UserService();

    public void handleUserInput() {

        System.out.println("Please type user full name");
        String fullName = scanner.nextLine();
        System.out.println("Please type user email");
        String email = scanner.nextLine();
        System.out.println("Please type user role");
        String role = scanner.nextLine();

        User user = new User(fullName, email, role);

        userService.createUser(user);

    }

}
