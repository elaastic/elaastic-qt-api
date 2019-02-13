package org.elaastic.qtapi.entities;

import org.hibernate.validator.constraints.UniqueElements;

import javax.management.relation.Role;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class User {

    //private transient springSecurityService;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String username;
    @NotBlank @UniqueElements @Pattern("/^[a-zA-Z0-9_\-]{1,15}$/")
    private String normalizedUsername;
    private String email;
    private String password;
    private String fullname;
    private boolean enabled;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean passwordExpired;

    private boolean canBeUserOwner = false;
    private User owner;
    private String clearPassword;

    //private Settings[] hasOne;

    static constraints = {
        firstName blank: false
        lastName blank: false
        normalizedUsername blank: false, unique: true, validator: { val ->
                (val ==~ /^[a-zA-Z0-9_\-]{1,15}$/) ?: 'user.normalizedUsername.invalid'
        }
        password blank: false, minSize: 4
        owner nullable: true
        email email: true, unique: true, nullable: true, validator: { targetEmail, obj ->
                targetEmail || obj.owner
        }
        settings nullable: true
    }

    static mapping = {
        password column: '`password`'
        version(true)
    }

    static transients = ['fullname', 'isTeacher', 'isLearner', "clearPassword"]

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
