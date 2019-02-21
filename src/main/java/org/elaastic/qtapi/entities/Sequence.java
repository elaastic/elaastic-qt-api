package org.elaastic.qtapi.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
public class Sequence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private int rank;

    @NotNull
    @Column(name="date_created")
    private Date dateCreated;
    @NotNull
    @Column(name="last_updated")
    private Date lastUpdated;

    @NotNull
    @ManyToOne
    private User owner;
    @NotNull
    @ManyToOne
    private Assignment assignment;
    @NotNull
    @ManyToOne
    private Statement statement;

    @ManyToOne
    private Interaction activeInteraction;

    @NotNull
    private String state;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sequence sequence = (Sequence) o;
        return getId() == sequence.getId() &&
                getRank() == sequence.getRank() &&
                getDateCreated().equals(sequence.getDateCreated()) &&
                getLastUpdated().equals(sequence.getLastUpdated()) &&
                getOwner().equals(sequence.getOwner()) &&
                getAssignment().equals(sequence.getAssignment()) &&
                getStatement().equals(sequence.getStatement()) &&
                Objects.equals(getActiveInteraction(), sequence.getActiveInteraction()) &&
                getState().equals(sequence.getState());
    }

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

}

