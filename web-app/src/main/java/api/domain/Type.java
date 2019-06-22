package api.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum Type {
    BUG("Bug"),
    STORY("Story"),
    TASK("Task");

    public String name;

    Type(String name) {
        this.name = name;
    }

    @JsonCreator
    public static Type fromString(@JsonProperty("type") String type) {
        for (Type t : Type.values()) {
            if (t.name.equalsIgnoreCase(type)) {
                return t;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

}
