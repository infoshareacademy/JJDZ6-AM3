package api.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
@Indexed
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message = "Title must not be empty")
    @Field
    private String title;
    private Type type;
    private String description;
    @NotBlank(message = "You have to specify priority")
    private Priority priority;
    private Integer storyPoints;
    @NotBlank(message = "You have to specify status")
    private Status status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assignee_id", referencedColumnName = "id")
    private User assignee;
    @Column(name = "project_id")
    private Long projectId;
    @Column(name = "sprint_id")
    private Long sprintId;

    public Task() {
    }

    @JsonCreator
    public Task(
            @JsonProperty("title") String title,
            @JsonProperty("type") String type,
            @JsonProperty("description") String description,
            @JsonProperty("priority") String priority,
            @JsonProperty("storyPoints") Integer storyPoints,
            @JsonProperty("status") Status status,
            @JsonProperty("assignee") User assignee,
            @JsonProperty("projectId") Long projectId,
            @JsonProperty("sprintId") Long sprintId) {
        this.title = title;
        this.type = Type.fromString(type);
        this.description = description;
        this.priority = Priority.fromString(priority);
        this.storyPoints = storyPoints;
        this.status = status;
        this.assignee = assignee;
        this.projectId = projectId;
        this.sprintId = sprintId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getSprintId() {
        return sprintId;
    }

    public void setSprintId(Long sprintId) {
        this.sprintId = sprintId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", storyPoints=" + storyPoints +
                ", status=" + status +
                ", assignee=" + assignee +
                ", projectId=" + projectId +
                '}';
    }
}
