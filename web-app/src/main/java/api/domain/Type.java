package api.domain;

public enum Type {
    BUG("Bug"),
    STORY("Story"),
    TASK("Task");

    public String name;

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
