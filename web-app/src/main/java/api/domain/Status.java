package api.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
    TO_DO("To do"),
    IN_PROGRESS("In progress"),
    READY_FOR_TESTING("Ready for testing"),
    DONE("Done");


    public String name;

    Status(String name) {
        this.name = name;
    }

    @JsonCreator
    public static Status fromString(@JsonProperty("status") String status) {
        for (Status s : Status.values()) {
            if (s.name.equalsIgnoreCase(status)) {
                return s;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
