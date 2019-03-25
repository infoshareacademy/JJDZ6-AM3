package utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class Setup {
    private static final String DIRECTORY = System.getProperty("user.dir");
    private static final Path USERS_JSON_FILE = Paths.get(DIRECTORY + "/Users.json");
    private static final Path BACKLOG_JSON_FILE = Paths.get(DIRECTORY + "/Backlog.json");
    private static final Path PROJECT_SCRUM_JSON_FILE = Paths.get(DIRECTORY + "/Scrum.json");

    public static void init() throws IOException {
        if (!Files.exists(USERS_JSON_FILE)) {
            InputStream resourceAsStream = Setup.class.getResourceAsStream("users.json");
            Files.copy(resourceAsStream, USERS_JSON_FILE);
        }
        if (!Files.exists(BACKLOG_JSON_FILE)) {
            InputStream resourceAsStream = Setup.class.getResourceAsStream("backlog.json");
            Files.copy(resourceAsStream, BACKLOG_JSON_FILE);
        }
        if (!Files.exists(PROJECT_SCRUM_JSON_FILE)) {
            InputStream resourceAsStream = Setup.class.getResourceAsStream("scrum.json");
            Files.copy(resourceAsStream, PROJECT_SCRUM_JSON_FILE);
        }
    }
}
