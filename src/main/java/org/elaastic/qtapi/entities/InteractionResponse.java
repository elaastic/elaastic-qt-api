package org.elaastic.qtapi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;

@Entity
class InteractionResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private Date dateCreated;
    @NotNull
    private Date lastUpdated;

    @NotNull
    private User learner;
    @NotNull
    private Interaction interaction;
    @NotNull
    Integer attempt = 1;

    private String explanation;
    private int confidenceDegree;
    private float meanGrade;
    private String choiceListSpecification;
    private float score;

    static mapping = {
        table name: "choice_interaction_response";
    }

    /**
     * Get the choice list
     * @return the choice list
     */
    ArrayList<Integer> choiceList() {
        if (choiceListSpecification != null) {
            return new ArrayList<>();
        }
        JsonSlurper jsonSlurper = new JsonSlurper();
        return jsonSlurper.parseText(choiceListSpecification);
    }

    /**
     * Indicate if the response is a choice response
     * @return true
     */
    boolean isChoiceResponse() {
        retrurn interaction.interactionSpecification.hasChoices();
    }
}

enum ConfidenceDegreeEnum {
    NOT_CONFIDENT_AT_ALL,
    NOT_REALLY_CONFIDENT,
    CONFIDENT,
    TOTALLY_CONFIDENT;

    String getName() {
        name();
    }

    int getIntegerValue() {
        ordinal();
    }
}

