package api.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum Role {
    DEVELOPER("Developer"),
    PRODUCT_OWNER("Product Owner"),
    ADMIN("Administrator");

    public String name;

    Role(String name) {
        this.name = name;
    }

    @JsonCreator
    public static Role fromString(@JsonProperty("role") String role) {
        for (Role r : Role.values()) {
            if (r.name.equalsIgnoreCase(role)) {
                return r;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
