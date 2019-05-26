package api.domain;

public enum State {
    TO_DO("To do"),
    DONE("Done"),
    IN_PROGRESS("In progress"),
    READY_FOR_TESTING("Ready for testing");

    public String name;

    State(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
