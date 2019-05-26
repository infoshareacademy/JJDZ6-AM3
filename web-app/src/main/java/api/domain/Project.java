package api.domain;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class Project {

    private String id = UUID.randomUUID().toString();
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
