package api.repository;

import api.domain.Task;
import com.google.gson.reflect.TypeToken;
import utils.FileUtils;

import javax.enterprise.context.ApplicationScoped;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TaskRepositoryImpl implements TaskRepository {
    private static final String DIRECTORY = System.getProperty("user.dir");
    private static final String BACKLOG_JSON_FILE = Paths.get(DIRECTORY + "/Backlog.json").toString();
    private static final Type collectionType = new TypeToken<List<Task>>() {}.getType();
    private List<Task> tasks = new ArrayList<>();

    public TaskRepositoryImpl() {
        this.tasks.addAll(FileUtils.readListFromJsonFile(BACKLOG_JSON_FILE, collectionType));
    }

    @Override
    public Task save(Task task) {
        this.tasks.add(task);
        persist();
        return task;
    }

    @Override
    public Task findById(String id) {
        return findAll()
                .stream()
                .filter(x -> x.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Task> findAll() {
        return this.tasks;
    }

    @Override
    public void delete(String id) {
        this.tasks.removeIf(task -> task.getId().equals(id));
        persist();
    }

    @Override
    public Task update(Task task) {
        this.tasks = findAll().stream()
                .map(entity -> {
                    if (entity.getId().equals(task.getId())) {
                        entity.setTitle(task.getTitle());
                        entity.setDescription(task.getDescription());
                        entity.setStoryPoints(task.getStoryPoints());
                        entity.setType(task.getType());
                        entity.setState(task.getState());
                        return entity;
                    }
                    return entity;
                })
                .collect(Collectors.toList());
        persist();
        return task;
    }

    private void persist() {
        FileUtils.saveListToJsonFile(this.tasks, BACKLOG_JSON_FILE);
    }
}
