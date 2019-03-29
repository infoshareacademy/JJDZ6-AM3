package userstory;

import java.util.ArrayList;

public class UserStory {

    private static int counter = 1;

    private int id;
    private String title;
    private Type issueType;
    private String description;
    private ArrayList<String> acceptanceCriteria;
    private Priority priority;
    private Integer storyPoints;
    private String State;

    public Integer getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(Integer storyPoints) {
        this.storyPoints = storyPoints;
    }

    public Integer getId() {
        return id;
    }

    public void setId() {
        this.id = UserStory.counter++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getIssueType() {
        return issueType;
    }

    public void setIssueType(Type issueType) {
        this.issueType = issueType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(ArrayList<String> acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "UserStory{" +
                "title='" + title + '\'' +
                ", issueType=" + issueType +
                ", description='" + description + '\'' +
                ", acceptanceCriteria=" + acceptanceCriteria +
                ", priority=" + priority +
                '}';
    }
}
