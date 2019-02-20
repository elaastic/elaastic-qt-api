package org.elaastic.qtapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

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
    @NotNull
    private String email;
    @NotBlank
    @Size(min = 4)
    private String password;
    @NotNull
    private byte canBeUserOwner;
    @ManyToOne
    private User owner;

    public String getFullname() {
        return firstName + " " + lastName;
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
        return canBeUserOwner == 1;
    }

    public void setCanBeUserOwner(byte canBeUserOwner) {
        this.canBeUserOwner = canBeUserOwner;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (isCanBeUserOwner() != user.isCanBeUserOwner()) return false;
        if (!getFirstName().equals(user.getFirstName())) return false;
        if (!getLastName().equals(user.getLastName())) return false;
        if (!getUsername().equals(user.getUsername())) return false;
        if (!getNormalizedUsername().equals(user.getNormalizedUsername())) return false;
        if (!getEmail().equals(user.getEmail())) return false;
        if (!getPassword().equals(user.getPassword())) return false;
        return getOwner() != null ? getOwner().equals(user.getOwner()) : user.getOwner() == null;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", normalizedUsername='" + normalizedUsername + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", canBeUserOwner=" + canBeUserOwner +
                ", owner=" + owner +
                '}';
    }
}
