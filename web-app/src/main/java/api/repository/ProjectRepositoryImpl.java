package api.repository;

import api.domain.Project;
import com.google.gson.reflect.TypeToken;
import utils.FileUtils;

import javax.enterprise.context.ApplicationScoped;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProjectRepositoryImpl implements ProjectRepository {
    private static final String DIRECTORY = System.getProperty("user.dir");
    private static final String SPRINTS_JSON_FILE = Paths.get(DIRECTORY + "/Projects.json").toString();
    private static final Type collectionType = new TypeToken<List<Project>>() {}.getType();
    private List<Project> projects = new ArrayList<>();

    public ProjectRepositoryImpl() {
        this.projects.addAll(FileUtils.readListFromJsonFile(SPRINTS_JSON_FILE, collectionType));
    }

    @Override
    public Project save(Project project) {
        this.projects.add(project);
        persist();
        return project;
    }

    @Override
    public Project findById(String id) {
        return findAll()
                .stream()
                .filter(x -> x.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Project> findAll() {
        return this.projects;
    }

    @Override
    public void delete(String id) {
        this.projects.removeIf(project -> project.getId().equals(id));
        persist();
    }

    @Override
    public Project update(Project project) {
        this.projects = findAll().stream()
                .map(entity -> {
                    if (entity.getId().equals(project.getId())) {
                        entity.setName(project.getName());
                        return entity;
                    }
                    return entity;
                })
                .collect(Collectors.toList());
        persist();
        return project;
    }

    private void persist() {
        FileUtils.saveListToJsonFile(this.projects, SPRINTS_JSON_FILE);
    }
}
