package org.elaastic.qtapi.entities;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    private String username;
    @NotBlank
    @UniqueElements
    @Pattern(regexp = "^[a-zA-Z0-9_-]{1,15}$")
    private String normalizedUsername;
    @Email
    @UniqueElements
    private String email;
    @NotBlank
    @Size(min = 4)
    private String password;
    @NotNull
    private boolean canBeUserOwner = false;
    @NotNull
    private User owner;

    public String getFullname() {
        return firstName + " " + lastName;
    }

    public String toString() {
        return getFullname();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNormalizedUsername() {
        return normalizedUsername;
    }

    public void setNormalizedUsername(String normalizedUsername) {
        this.normalizedUsername = normalizedUsername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCanBeUserOwner() {
        return canBeUserOwner;
    }

    public void setCanBeUserOwner(boolean canBeUserOwner) {
        this.canBeUserOwner = canBeUserOwner;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
