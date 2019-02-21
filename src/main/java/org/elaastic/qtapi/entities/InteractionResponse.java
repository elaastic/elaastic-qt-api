package org.elaastic.qtapi.entities;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.internal.jline.internal.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.*;

@Entity
@Table(name = "choice_interaction_response")
public class InteractionResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name="date_created")
    private Date dateCreated;
    @NotNull
    @Column(name="last_updated")
    private Date lastUpdated;

    @NotNull
    @ManyToOne
    private User learner;
    @NotNull
    @ManyToOne
    private Interaction interaction;
    @NotNull
    private int attempt;

    private String explanation;
    @Column(name = "confidence_degree")
    private Integer confidenceDegree;
    @Column(name = "mean_grade")
    private Float meanGrade;
    @Column(name = "choice_list_specification")
    private String choiceListSpecification;
    private float score;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InteractionResponse that = (InteractionResponse) o;
        return getId() == that.getId() &&
                getAttempt() == that.getAttempt() &&
                getConfidenceDegree() == that.getConfidenceDegree() &&
                Float.compare(that.getMeanGrade(), getMeanGrade()) == 0 &&
                Float.compare(that.getScore(), getScore()) == 0 &&
                getDateCreated().equals(that.getDateCreated()) &&
                getLastUpdated().equals(that.getLastUpdated()) &&
                getLearner().equals(that.getLearner()) &&
                getInteraction().equals(that.getInteraction()) &&
                Objects.equals(getExplanation(), that.getExplanation()) &&
                Objects.equals(getChoiceListSpecification(), that.getChoiceListSpecification());
    }

    /**
     * Get the choice list
     * @return the choice list
     */
    public ArrayList<Integer> choiceList() {
        if (choiceListSpecification != null) {
            return new ArrayList<>();
        }
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Integer> res = new ArrayList<>();

        try {
            //JsonSlurper jsonSlurper = new JsonSlurper();
            res = mapper.readValue(choiceListSpecification, new TypeReference<ArrayList<Integer>>(){});

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

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

    public User getLearner() {
        return learner;
    }

    public void setLearner(User learner) {
        this.learner = learner;
    }

    public Interaction getInteraction() {
        return interaction;
    }

    public void setInteraction(Interaction interaction) {
        this.interaction = interaction;
    }

    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Integer getConfidenceDegree() {
        return confidenceDegree;
    }

    public void setConfidenceDegree(Integer confidenceDegree) {
        this.confidenceDegree = confidenceDegree;
    }

    public Float getMeanGrade() {
        return meanGrade;
    }

    public void setMeanGrade(Float meanGrade) {
        this.meanGrade = meanGrade;
    }

    public String getChoiceListSpecification() {
        return choiceListSpecification;
    }

    public void setChoiceListSpecification(String choiceListSpecification) {
        this.choiceListSpecification = choiceListSpecification;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}

