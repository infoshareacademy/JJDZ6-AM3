package api.repository;

import api.domain.Sprint;
import com.google.gson.reflect.TypeToken;
import utils.FileUtils;

import javax.enterprise.context.ApplicationScoped;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class SprintRepositoryImpl implements SprintRepository {
    private static final String DIRECTORY = System.getProperty("user.dir");
    private static final String SPRINTS_JSON_FILE = Paths.get(DIRECTORY + "/Sprint.json").toString();
    private static final Type collectionType = new TypeToken<List<Sprint>>() {}.getType();
    private List<Sprint> sprints = new ArrayList<>();

    public SprintRepositoryImpl() {
        this.sprints.addAll(FileUtils.readListFromJsonFile(SPRINTS_JSON_FILE, collectionType));
    }

    @Override
    public Sprint save(Sprint sprint) {
        this.sprints.add(sprint);
        persist();
        return sprint;
    }

    @Override
    public Sprint findById(String id) {
        return findAll()
                .stream()
                .filter(x -> x.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Sprint> findAll() {
        return this.sprints;
    }

    @Override
    public void delete(String id) {
        this.sprints.removeIf(sprint -> sprint.getId().equals(id));
        persist();
    }

    @Override
    public Sprint update(Sprint entity) {
        return null;
    }

    private void persist() {
        FileUtils.saveListToJsonFile(this.sprints, SPRINTS_JSON_FILE);
    }
}
