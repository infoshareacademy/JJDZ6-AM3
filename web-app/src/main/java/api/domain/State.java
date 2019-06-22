package api.domain;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

public enum State {
    ACTIVE("Active"),
    CLOSED("Closed");

    public String state;

    private State(String state) {
        this.state = state;
    }

    @JsonbCreator
    public static State fromString(@JsonbProperty("state") String state) {
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
