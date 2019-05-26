package api.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class User {

    private String id;

    @NotNull
    private String fullName;
    @Email(message = "This is not a valid email")
    private String email;
    @NotNull
    private Role role;

    public User() {
    }

    public User(String fullName, String email, Role role) {
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
