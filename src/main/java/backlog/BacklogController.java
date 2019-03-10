package backlog;

import userstory.UserStory;
import utils.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class BacklogController {

    private static final String BACKLOG_JSON_FILE = Paths.get("Backlog.json").toString();
    private static final Path IMPORT_FILE = Paths.get("user-stories.json");

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
        ArrayList<UserStory> backlog = FileUtils.readListFromJsonFile(BACKLOG_JSON_FILE);
        ArrayList<UserStory> importedStories = FileUtils.readListFromJsonFile(path);

        backlog.addAll(importedStories);

        FileUtils.saveListToJsonFile(backlog, BACKLOG_JSON_FILE);
    }
}
