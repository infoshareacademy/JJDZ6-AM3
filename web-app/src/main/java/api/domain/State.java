package api.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum State {
    ACTIVE("Active"),
    CLOSED("Closed");

    public String state;

    private State(String state) {
        this.state = state;
    }

    @JsonCreator
    public static State fromString(@JsonProperty("state") String state) {
        for (State s : State.values()) {
            if (s.state.equalsIgnoreCase(state)) {
                return s;
            }
        }
        return null;
    }

    public String getState() {
        return state;
    }

}
