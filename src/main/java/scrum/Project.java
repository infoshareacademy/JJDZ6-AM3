package scrum;

/**
 * Created by Marek on 19.03.2019.
 */
public class Project {

    private String fullNameOfProject;
    private String key;
    private String fullNameOfProductOwner;
    private String developerFullName;

    public String getFullNameOfProject() {
        return fullNameOfProject;
    }

    public void setFullNameOfProject(String fullNameOfProject) {
        this.fullNameOfProject = fullNameOfProject;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFullNameOfProductOwner() {
        return fullNameOfProductOwner;
    }

    public void setFullNameOfProductOwner(String fullNameOfProductOwner) {
        this.fullNameOfProductOwner = fullNameOfProductOwner;
    }

    public String getDeveloperFullName() {
        return developerFullName;
    }

    public void setDeveloperFullName(String developerFullName) {
        this.developerFullName = developerFullName;
    }

    @Override
    public String toString() {
        return "Project{" +
                "fullNameOfProject='" + fullNameOfProject + '\'' +
                ", key='" + key + '\'' +
                ", fullNameOfProductOwner='" + fullNameOfProductOwner + '\'' +
                ", developerFullName='" + developerFullName + '\'' +
                '}';
    }
}
