package repository;

import com.google.gson.reflect.TypeToken;
import context.ScrumBoardContext;
import domain.Task;
import utils.FileUtils;

import javax.enterprise.context.RequestScoped;
import java.lang.reflect.Type;
import java.util.List;

@RequestScoped
public class TasksRepository {

    private static final Type collectionType = new TypeToken<List<Task>>() {}.getType();

    public List<Task> getTasks() {
        List<Task> tasks = FileUtils.readListFromJsonFile(ScrumBoardContext.BACKLOG_JSON_FILE, collectionType);
        return tasks;

    }
}
