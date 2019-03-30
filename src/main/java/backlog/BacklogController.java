package backlog;

import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import userstory.UserStory;
import utils.FileUtils;
import utils.Styles;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class BacklogController {
    private static final String DIRECTORY = System.getProperty("user.dir");
    private static final String BACKLOG_JSON_FILE = Paths.get(DIRECTORY + "/Backlog.json").toString();
    private static final Path IMPORT_FILE = Paths.get(DIRECTORY + "/user-stories.json");
    private static final Type collectionType = new TypeToken<List<UserStory>>() {
    }.getType();

    private void inRead() {
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<UserStory> enrichId(List<UserStory> backlog) {
        return backlog.stream().map(x -> {
            x.setId();
            return x;
        }).collect(Collectors.toList());
    }

    public UserStory enrichSprintId(UserStory task, Integer sprintId) {
        task.setSprintId(sprintId);
        return task;
    }

    public List<UserStory> updateCollection(Integer id, UserStory updatedUserStory) {
        return getTasks().stream()
                .map(x -> {
                    if (x.getId().equals(id)) {
                        return updatedUserStory;
                    }
                    return x;
                }).collect(Collectors.toList());
    }

    public void updateCollection(List<Integer> ids, Integer sprintId) {
        List<UserStory> updatedUserStories = getTasks().stream()
                .map(x -> {
                    if (ids.contains(x.getId())) {
                        return enrichSprintId(x, sprintId);
                    }
                    return x;
                }).collect(Collectors.toList());
        FileUtils.saveListToJsonFile(enrichId(updatedUserStories), BACKLOG_JSON_FILE);
    }

    public UserStory findById(Integer id) {
        return getTasks().stream()
                .filter(x -> x.getId().equals(id))
                .findAny().orElse(null);
    }

    public void importTasks() {

        System.out.println(Styles.INFO + "Please provide import file with filename user-stories.json" +
                " in app directory" + Styles.RESET);
        System.out.println();
        System.out.println(Styles.RESET + "Press Enter to continue...");
        inRead();

        if (!Files.exists(IMPORT_FILE)) {
            System.out.println(Styles.ERROR + "File user-stories.json not found!");
            return;
        }
        String path = IMPORT_FILE.toString();
        List<UserStory> backlog = getTasks();
        List<UserStory> importedStories = FileUtils.readListFromJsonFile(path, collectionType);

        backlog.addAll(importedStories);

        FileUtils.saveListToJsonFile(enrichId(backlog), BACKLOG_JSON_FILE);
    }

    public void showBacklogTasks() {
        List<UserStory> backlog = FileUtils.readListFromJsonFile(BACKLOG_JSON_FILE, collectionType);

        String leftAlignFormat = "| %-2d | %-25s | %-7s | %-28s |%n";

        System.out.format("+----+---------------------------+---------+------------------------------+%n");
        System.out.format("| ID | Title                     | Type    | Description                  |%n");
        System.out.format("+----+---------------------------+---------+------------------------------+%n");

        for (UserStory userStory : backlog) {
            Integer id = userStory.getId();
            String title = StringUtils.abbreviate(userStory.getTitle(), 25);
            String type = StringUtils.abbreviate(userStory.getIssueType().getType(), 7);
            String description = StringUtils.abbreviate(userStory.getDescription(), 20);

            System.out.format(leftAlignFormat, id, title, type, description);
        }
        System.out.format("+----+---------------------------+---------+------------------------------+%n");
    }

    public List<UserStory> getTasks() {
        return FileUtils.readListFromJsonFile(BACKLOG_JSON_FILE, collectionType);
    }

    public List<UserStory> getTasksByIds(List ids) {
        List<UserStory> backlog = FileUtils.readListFromJsonFile(BACKLOG_JSON_FILE, collectionType);
        return backlog.stream().filter(task -> ids.contains(task.getId())).collect(Collectors.toList());
    }


}
