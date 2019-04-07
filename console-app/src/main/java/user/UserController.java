package user;

import com.google.gson.reflect.TypeToken;
import user.model.User;
import utils.FileUtils;
import utils.Styles;

import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class UserController {

    private Scanner scanner = new Scanner(System.in);
    private static final String USERS_JSON_FILE = Paths.get("Users.json").toString();
    private static final Type collectionType = new TypeToken<List<User>>() {
    }.getType();

    public void createUser() {

        List<User> users = FileUtils.readListFromJsonFile(USERS_JSON_FILE, collectionType);

        System.out.println(Styles.INFO + "Please type user full name" + Styles.RESET);
        String fullName = scanner.nextLine();
        System.out.println(Styles.INFO + "Please type user email" + Styles.RESET);
        String email = scanner.nextLine();
        System.out.println(Styles.INFO + "Please type user role" + Styles.RESET);
        String role = scanner.nextLine();

        User user = new User(fullName, email, role);

        users.add(user);

        System.out.println("ALL USERS: ");

        for (User person : users) {
            System.out.println(person.toString());
        }

        FileUtils.saveListToJsonFile(users, USERS_JSON_FILE);

    }

    public void getUserByRole() {

        List<User> users = FileUtils.readListFromJsonFile(USERS_JSON_FILE, collectionType);

        System.out.println(Styles.INFO + "Type a name and surname of Developer from the list or leave the field blank:" + Styles.RESET);

        String chooseRole = "Developer";

        Map<Integer, User> map = new TreeMap<>();
        int count = 0;
        for (User user : users) {

            if (chooseRole.equals(user.getRole())) {
                count++;
                map.put(count, user);
            }
        }

        for (Integer key : map.keySet()) {
            System.out.println(key + " " + map.get(key).getFullName());
        }
    }

}
