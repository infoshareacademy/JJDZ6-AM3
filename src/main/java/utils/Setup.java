package utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class Setup {
    private static final Path USERS_JSON_FILE = Paths.get("Users.json");

    public static void init() throws IOException {
        if (!Files.exists(USERS_JSON_FILE)) {
            InputStream resourceAsStream = Setup.class.getResourceAsStream("users.json");
            Files.copy(resourceAsStream, USERS_JSON_FILE);
        }
    }

}
