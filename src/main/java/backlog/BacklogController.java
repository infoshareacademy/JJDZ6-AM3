package backlog;

import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import userstory.UserStory;
import utils.FileUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

    public void importTasks() {

        System.out.println("Please provide import file with filename user-stories.json in app directory");
        System.out.println();
        System.out.println("Press Enter to continue...");
        inRead();

        if (!Files.exists(IMPORT_FILE)) {
            System.out.println("File user-stories.json not found!");
            return;
        }
        String path = IMPORT_FILE.toString();
        List<UserStory> backlog = FileUtils.readListFromJsonFile(BACKLOG_JSON_FILE, collectionType);
        List<UserStory> importedStories = FileUtils.readListFromJsonFile(path, collectionType);

        backlog.addAll(importedStories);

        FileUtils.saveListToJsonFile(backlog, BACKLOG_JSON_FILE);
    }

    public void showBacklogTasks() {
        List<UserStory> backlog = FileUtils.readListFromJsonFile(BACKLOG_JSON_FILE, collectionType);

        String leftAlignFormat = "| %-25s | %-7s | %-20s |%n";

        System.out.format("+---------------------------+---------+----------------------+%n");
        System.out.format("| Title                     | Type    | Description          |%n");
        System.out.format("+---------------------------+---------+----------------------+%n");

        for (UserStory userStory : backlog) {
            String title = StringUtils.abbreviate(userStory.getTitle(), 25);
            String type = StringUtils.abbreviate(userStory.getIssueType().getType(), 7);
            String description = StringUtils.abbreviate(userStory.getDescription(), 20);

            System.out.format(leftAlignFormat, title, type, description);
        }
        System.out.format("+---------------------------+---------+----------------------+%n");
    }

    public ArrayList showBacklogTasks(int x) {
        List<UserStory> backlog = FileUtils.readListFromJsonFile(BACKLOG_JSON_FILE, collectionType);
        ArrayList<String> listToSprint = new ArrayList<>();

        for (UserStory userStory : backlog) {

            String title = StringUtils.abbreviate(userStory.getTitle(), 25);
            String type = StringUtils.abbreviate(userStory.getIssueType().getType(), 7);
            String description = StringUtils.abbreviate(userStory.getDescription(), 20);
            listToSprint.add(title);
            listToSprint.add(type);
            listToSprint.add(description);


        }
        return listToSprint;
    }

}
