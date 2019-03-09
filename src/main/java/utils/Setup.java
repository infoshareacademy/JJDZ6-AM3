package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class Setup {
    private static final String USERS_JSON_FILE = Paths.get( "." + File.separator + "Users.json").toString();
    private static final String USERS_CONFIG = Setup.class.getResource(File.separator + "users.json").getPath();
    private static File file = new File(USERS_JSON_FILE);

    public static void init() throws IOException {
        if (!Files.exists(file.toPath())) {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            String config = new String(Files.readAllBytes(Paths.get(USERS_CONFIG)));
            writer.write(config);
            writer.close();
        }
    }

}
