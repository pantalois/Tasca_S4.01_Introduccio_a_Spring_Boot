package cat.itacademy.s04.t01.userapi.model;

import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String email;

    public User() {

        this.id = UUID.randomUUID();
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public UUID getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
    }
}
