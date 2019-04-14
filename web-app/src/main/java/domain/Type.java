package domain;

public enum Type {
    BUG("Bug"),
    STORY("Story"),
    TASK("Task");

    String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
