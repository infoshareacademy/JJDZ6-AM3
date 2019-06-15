package api.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.UUID;

@Entity
public class Project {

    private String id = UUID.randomUUID().toString();
    @NotBlank(message = "Project name can not be empty")
    private String name;
    private Set<Task> tasks;

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Project() {
    }

    public Project(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
