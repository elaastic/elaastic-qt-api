package org.elaastic.qtapi.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class PeerGrading {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name="date_created")
    private Date dateCreated;
    @NotNull
    @Column(name="last_updated")
    private Date lastUpdated;

    private Float grade; // Can be null
    private String annotation; // Can be null

    @NotNull
    @ManyToOne
    private User grader;
    @NotNull
    @ManyToOne
    private InteractionResponse response;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public User getGrader() {
        return grader;
    }

    public void setGrader(User grader) {
        this.grader = grader;
    }

    public InteractionResponse getResponse() {
        return response;
    }

    public void setResponse(InteractionResponse response) {
        this.response = response;
    }
}
