package api.domain;

public enum Status {
    TO_DO("To do"),
    DONE("Done"),
    IN_PROGRESS("In progress"),
    READY_FOR_TESTING("Ready for testing");

    public String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
