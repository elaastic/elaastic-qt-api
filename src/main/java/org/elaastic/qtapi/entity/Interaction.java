package org.elaastic.qtapi.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

class Interaction {

    public static final String EMPTY_SPECIFICATION = "empty";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String interactionType;
    @NotNull
    private Integer rank;
    @NotNull
    private String specification;

    private Date dateCreated;
    private Date lastUpdated;

    @NotNull
    private User owner;
    @NotNull
    private Sequence sequence; // TODO need to be in InteractionType enum
    @NotNull
    private String state; // TODO need to be in StateType enum

    private String results; // Can be null
    private String explanationRecommendationMapping; // Can be null

//    static transients = ['interactionSpecification']


    /**
     * Get the interaction specification object
     * @return the interaction specification
     */
    public JsonSpecification getInteractionSpecification() {
        if (isResponseSubmission()) {
            return new ResponseSubmissionSpecification(specification)
        } else if (isEvaluation()) {
            return new EvaluationSpecification(specification)
        }
        null
    }


    /**
     * Indicate if the interaction is read
     * @return true if the interaction is read
     */
    boolean isRead() {
        return interactionType == InteractionType.Read.toString();
    }

    /** // TODO ask if it's the good methode to get top responses
     *
     * @return the explanation recommendation map
     */
    Map<String, List<Long>> explanationRecommendationMap() {
        if (!explanationRecommendationMapping) {
            return [:]
        }
        JsonSlurper jsonSlurper = new JsonSlurper()
        def res = jsonSlurper.parseText(explanationRecommendationMapping)
        res
    }



}

enum InteractionType {
    ResponseSubmission,
    Evaluation,
    Read
}