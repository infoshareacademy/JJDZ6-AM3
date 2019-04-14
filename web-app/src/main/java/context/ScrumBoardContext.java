package context;

import java.nio.file.Paths;

public class ScrumBoardContext {
    private static final String DIRECTORY = System.getProperty("user.dir");
    public static final String BACKLOG_JSON_FILE = Paths.get(DIRECTORY + "/Tasks.json").toString();


}

