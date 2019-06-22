package api.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum Priority {
    HIGHEST("Highest"),
    HIGH("High"),
    LOWEST("Lowest"),
    LOW("Low");

    public String priority;

    Priority(String priority) {
        this.priority = priority;
    }

    public String getName() {
        return priority;
    }

    @JsonCreator
    public static Priority fromString(@JsonProperty("priority") String type) {
        for (Priority t : Priority.values()) {
            if (t.priority.equalsIgnoreCase(type)) {
                return t;
            }
        }
        return null;
    }
}
