package sprint;

import backlog.BacklogController;
import com.google.gson.reflect.TypeToken;
import userstory.UserStory;
import utils.FileUtils;
import utils.Styles;

import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SprintController {

    private Scanner scanner = new Scanner(System.in);
    private static final String SPRINT_JSON_FILE = Paths.get("Sprints.json").toString();
    private static final Type collectionType = new TypeToken<List<Sprint>>() {
    }.getType();
    private BacklogController backlogController = new BacklogController();

    private List<Sprint> enrichId(List<Sprint> sprints) {
        return sprints.stream().map(x -> {
            x.setId();
            return x;
        }).collect(Collectors.toList());
    }

    public void createSprint() {

        List<Sprint> sprints = FileUtils.readListFromJsonFile(SPRINT_JSON_FILE, collectionType);

        System.out.println(Styles.INFO + "Please type sprint name: " + Styles.RESET);
        String name = scanner.nextLine();
        System.out.println(Styles.INFO + "Please type sprint end date in format dd-MM-yyyy" + Styles.RESET);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String endDate = scanner.nextLine();
        LocalDate parsedDate = LocalDate.parse(endDate, format);

        Sprint sprint = new Sprint(name, parsedDate);

        sprints.add(sprint);

        backlogController.showBacklogTasks();

        System.out.println(Styles.INFO + "Please type comma separated ids you want to put into " +
                "current sprint" + Styles.RESET);
        String ids = scanner.nextLine();
        List<Integer> taskIds = Arrays.asList(ids.split(",")).stream()
                .map(i -> Integer.parseInt(i.trim())).collect(Collectors.toList());

        enrichId(sprints);

        assignTasks(taskIds, sprint);

        FileUtils.saveListToJsonFile(sprints, SPRINT_JSON_FILE);

    }

    public void assignTasks(List<Integer> ids, Sprint sprint) {
        List<UserStory> userStories = backlogController.getTasksByIds(ids);
        backlogController.updateCollection(ids, sprint.getId());

        sprint.setTasks(userStories);
    }

}
