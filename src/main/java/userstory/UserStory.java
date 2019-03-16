package userstory;

import java.util.ArrayList;

public class UserStory {
    private String title;
    private Type issueType;
    private String description;
    private ArrayList<String> acceptanceCriteria;
    private Priority priority;

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
