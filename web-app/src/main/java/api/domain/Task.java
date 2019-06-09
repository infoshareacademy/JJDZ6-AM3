package api.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Task {

    private String id;
    @NotBlank(message = "Title must not be empty")
    private String title;
    private Type type;
    private String description;
    private Priority priority;
    private Integer storyPoints;
    private State state;
    private User assignee;
    private String projectId;

    public Task() {
    }

    public Task(String title, Type type, State state) {
        this.title = title;
        this.type = type;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Integer getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(Integer storyPoints) {
        this.storyPoints = storyPoints;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", storyPoints=" + storyPoints +
                ", state=" + state +
                ", assignee=" + assignee +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}
