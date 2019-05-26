package api.domain;

public enum Role {
    DEVELOPER("Developer"),
    PRODUCT_OWNER("Product Owner"),
    ADMIN("Administrator");

    public String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
