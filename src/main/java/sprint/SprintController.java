package sprint;

import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import userstory.UserStory;
import utils.FileUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
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

            System.out.println("Please type end of date the Sprint for example: 31-12-2019");
            Scanner scanner = new Scanner(System.in);
            String sprintEndDate = scanner.nextLine();

            Sprint sprint = new Sprint(sprintName,sprintEndDate );

            sprints.add(sprint);

            FileUtils.saveListToJsonFile(sprints, SPRINT_JSON_FILE);

        }
}


