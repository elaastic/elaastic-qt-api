package org.elaastic.qtapi.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Sequence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private int rank;

    @NotNull
    private Date dateCreated;
    @NotNull
    private Date lastUpdated;

    @NotNull
    private User owner;
    @NotNull
    private Assignment assignment;
    @NotNull
    private Statement statement;

    private Interaction activeInteraction;

    @NotNull
    private String state = StateType.beforeStart.toString();
    @NotNull
    private Boolean resultsArePublished; // = false;// TODO Question, all question that are publish are good for test ?

    // TODO RQT dans la base pour avoir tout les Interaction lier a la sequence

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public Interaction getActiveInteraction() {
        return activeInteraction;
    }

    public void setActiveInteraction(Interaction activeInteraction) {
        this.activeInteraction = activeInteraction;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getResultsArePublished() {
        return resultsArePublished;
    }

    public void setResultsArePublished(Boolean resultsArePublished) {
        this.resultsArePublished = resultsArePublished;
    }
}

enum StateType {
    beforeStart,
    show,
    afterStop;
}
