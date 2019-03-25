package scrum;

import com.google.gson.reflect.TypeToken;
import user.model.User;
import utils.FileUtils;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Marek on 25.03.2019.
 */
public class ReadModel {

    private static final String USERS_JSON_FILE = Paths.get("Users.json").toString();
    private static final Type collectionType = new TypeToken<List<User>>() {}.getType();

    public void getDeveloper() {

        List<User> users = FileUtils.readListFromJsonFile(USERS_JSON_FILE, collectionType);

        System.out.println("Type a name and surname of Developer from the list:");

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
