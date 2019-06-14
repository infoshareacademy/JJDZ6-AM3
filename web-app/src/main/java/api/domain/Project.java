package api.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
public class Project {

    private String id = UUID.randomUUID().toString();
    @NotBlank(message = "Project name can not be empty")
    private String name;
    private String productOwnerFullName;
    private String developerFullName;

    public Project(){
    }

    public Project(String id, String name, String productOwnerFullName, String developerFullName) {
        this.id = id;
        this.name = name;
        this.productOwnerFullName = productOwnerFullName;
        this.developerFullName = developerFullName;
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

    public String getProductOwnerFullName() {
        return productOwnerFullName;
    }

    public void setProductOwnerFullName(String productOwnerFullName) {
        this.productOwnerFullName = productOwnerFullName;
    }

    public String getDeveloperFullName() {
        return developerFullName;
    }

    public void setDeveloperFullName(String developerFullName) {
        this.developerFullName = developerFullName;
    }
}
