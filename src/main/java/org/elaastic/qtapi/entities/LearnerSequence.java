package org.elaastic.qtapi.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
public class LearnerSequence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @ManyToOne
    private User learner;
    @NotNull
    @ManyToOne
    private Sequence sequence;
    @NotNull
    @ManyToOne
    private Interaction activeInteraction;

    @NotNull
    @Column(name="date_created")
    private Date dateCreated;
    @NotNull
    @Column(name="last_updated")
    private Date lastUpdated;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LearnerSequence that = (LearnerSequence) o;
        return getId() == that.getId() &&
                getLearner().equals(that.getLearner()) &&
                getSequence().equals(that.getSequence()) &&
                getActiveInteraction().equals(that.getActiveInteraction()) &&
                getDateCreated().equals(that.getDateCreated()) &&
                getLastUpdated().equals(that.getLastUpdated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLearner(), getSequence(), getActiveInteraction(), getDateCreated(), getLastUpdated());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getLearner() {
        return learner;
    }

    public void setLearner(User learner) {
        this.learner = learner;
    }

    public Sequence getSequence() {
        return sequence;
    }

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }

    public Interaction getActiveInteraction() {
        return activeInteraction;
    }

    public void setActiveInteraction(Interaction activeInteraction) {
        this.activeInteraction = activeInteraction;
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
}
