package org.elaastic.qtapi.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

class Interaction {

    public static final String EMPTY_SPECIFICATION = "empty";

    private String interactionType;
    private Integer rank;
    private String specification;

    private Date dateCreated;
    private Date lastUpdated;

    private User owner;
    private Sequence sequence;
    private String state = StateType.beforeStart.name();

    private String results;
    private String explanationRecommendationMapping;

    // TODO in list constraint
    static constraints = {
        interactionType inList: InteractionType.values()*.name()
        state inList: StateType.values()*.name()
        results nullable: true
        explanationRecommendationMapping nullable: true
    }

//    static transients = ['interactionSpecification', 'interactionResultListService', 'responseRecommendationService', 'interactionService']

    /**
     * Get the result matrix as a list of float
     * @return the result matrix as a list of float for each attempt
     */
    Map<String, List<Float>> resultsByAttempt() {
        if (!results) {
            return [:]
        }
        JsonSlurper jsonSlurper = new JsonSlurper()
        jsonSlurper.parseText(results)
    }

    boolean hasAnyResult() {
        Map results = resultsByAttempt();

        return results["1"]?.size() || results["2"]?.size()
    }

    /**
     * Get the results for the last attempt
     * @return the results
     */
    List<Float> resultsOfLastAttempt() {
        def resultsByAttempt = resultsByAttempt()
        List<Float> res
        if (resultsByAttempt["2"]) {
            res = resultsByAttempt["2"]
        } else if (resultsByAttempt["1"]) {
            res = resultsByAttempt["1"]
        } else {
            res = []
        }
        res
    }

    InteractionService interactionService;

    /**
     * Process to perform after stop
     */
    void doAfterStop() {
        state = StateType.afterStop.name()
        if (isResponseSubmission()) {
            doAfterStopOfResponseSubmission()
        }
        if (isEvaluation()) {
            doAfterStopOfEvaluation()
        }
    }

    private void doAfterStopOfEvaluation() {
        def respSubmInter = sequence.responseSubmissionInteraction
        if (sequence.statement.hasChoices()) {
            respSubmInter.updateResults(2)
            respSubmInter.save()
        }
        int attemptEvaluated = sequence.executionIsFaceToFace() ? 1 : 2
        respSubmInter.findAllEvaluatedResponses(attemptEvaluated).each {
            interactionService.updateMeanGradeOfResponse(it)
        }
    }

    private void doAfterStopOfResponseSubmission() {
        if (sequence.statement.hasChoices()) {
            updateResults()
        }
        if (interactionSpecification.studentsProvideExplanation) {
            updateExplanationRecommendationMapping()
        }
        save()
    }

    /**
     * Find all evaluated responses for the current interaction
     * @return the list of evaluated responses
     */
    public List<InteractionResponse> findAllEvaluatedResponses(int attempt = 1) {
        InteractionResponse.findAllByInteractionAndAttempt(this, attempt)
    }

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

//    /**
//     * Indicate if the interaction is a response submission
//     * @return true if the interaction is a response submission
//     */
//    boolean isResponseSubmission() {
//        return interactionType == InteractionType.ResponseSubmission.toString();
//    }
//
//    /**
//     * Indicate if the interaction is an evaluation
//     * @return true if the interaction is an evaluation
//     */
//    boolean isEvaluation() {
//        return sinteractionType == InteractionType.Evaluation.toString();
//    }

    /**
     * Indicate if the interaction is read
     * @return true if the interaction is read
     */
    boolean isRead() {
        return interactionType == InteractionType.Read.toString();
    }

    /**
     * Calculate the number of choice interaction responses for the current interaction
     * @return the number of responses
     */
    Integer interactionResponseCount(int attempt = 1) {
        int res = InteractionResponse.countByInteractionAndAttempt(this, attempt);
        return res;
    }

    /**
     * Count the number of evaluations
     * @return the count
     */
    Integer evaluationCount() {
        def count = PeerGrading.executeQuery(
                '''
        select count(distinct pg.grader) from PeerGrading pg
        where pg.response in (from InteractionResponse resp where resp.interaction = ?)
        ''',
                [this])
        count[0]
    }

    /**
     * Check if a user has already given a response for the current interaction
     * @param user the user
     * @return true if user has already given a response
     */
    boolean hasResponseForUser(User user, int attempt = 1) {
        InteractionResponse.countByInteractionAndLearnerAndAttempt(this, user, attempt) > 0
    }

//    /**
//     * Get the response for the given user
//     * @param user the user
//     * @return the response
//     */
//    InteractionResponse responseForUser(User user, int attempt = 1) {
//        return InteractionResponse.findByInteractionAndLearnerAndAttempt(this, user, attempt);
//    }

    /**
     * Get the last response for the given user
     * @param user the user
     * @return the last response
     */
    InteractionResponse lastAttemptResponseForUser(User user) {
        def res = InteractionResponse.findByInteractionAndLearnerAndAttempt(this, user, 2)
        if (!res) {
            res = InteractionResponse.findByInteractionAndLearnerAndAttempt(this, user, 1)
        }
        res
    }

    /**
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