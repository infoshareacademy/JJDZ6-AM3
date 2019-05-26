package api.domain;

import javax.persistence.Entity;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
public class Sprint {

    private String id = UUID.randomUUID().toString();
    private String name;
    private Date startDate;
    private Date endDate;
    private Set<Task> tasks;

    public Sprint() {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void addTaskToSprint(Task task) {
        this.tasks.add(task);
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
