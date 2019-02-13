package org.elaastic.qtapi.entities;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
public class User {

    //private transient springSecurityService;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String username;
    @NotBlank @UniqueElements @Pattern(regexp = "^[a-zA-Z0-9_-]{1,15}$")
    private String normalizedUsername;
    @Email @UniqueElements @Nullable
    private String email;
    @NotBlank @Size(min = 4)
    private String password;
    @Transient
    private String fullname;
    private boolean enabled;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean passwordExpired;

    private boolean canBeUserOwner = false;
    @Nullable
    private User owner;
    private String clearPassword;

    //private Settings[] hasOne;

    public String getFullname() {
        return firstName + " " + lastName;
    }

    public String toString() {
        return username;
    }

    /**
     *
     * @return true if the user is a learner
     */
    public boolean isLearner() {
        return UserRole.get(this.id, RoleEnum.STUDENT_ROLE.id);
    }

    /**
     *
     * @return true if the user is teacher
     */
    public boolean isTeacher() {
        return UserRole.get(this.id, RoleEnum.TEACHER_ROLE.id);
    }

    /**
     *
     * @return true if the user is admin
     */
    public boolean isAdmin() {
        return UserRole.get(this.id, RoleEnum.ADMIN_ROLE.id);
    }

    /**
     * Check if a user is registered in an assignment
     * @param assignment the assignment
     * @return true if the user is registered in the given assignment
     */
    public boolean isRegisteredInAssignment(Assignement assignment) {
        return LearnerAssignment.findByLearnerAndAssignment(this,assignment);
    }

//    public Set<Role> getAuthorities() {
//        Set<Role> res = UserRole.findAllByUser(this).map(Role::).collect(Collectors.toCollection(TreeSet::new));
//                .collect(toSet(), );
//        res*.roleName
//                res
//    }
}
