package org.elaastic.qtapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Sequance {

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
    private Boolean resultsArePublished; // = false; // TODO Question, all question that are publish are good for test ?

//    {
//        state inList:StateType.values() *.name()
//    }

    @OneToMany(mappedBy = "interaction")
    @JsonIgnore
    ArrayList<Interaction> interactions;

    /**
     * All the interaction of the sequance
     *
     * @return the interactions
     */
    public ArrayList<Interaction> getInteractions() {
        return interactions;
    }

    // TODO RQT dans la base pour avoir tout les Interaction lier a la sequence

    /**
     * Get the response submission interaction
     *
     * @return the response submission interaction
     */
    public Interaction getResponseSubmissionInteraction() {
        return interactions != null ? interactions.get(0) : null;
    }


    // TODO Question if needed to keep it
    DefaultResponseRecommendationService responseRecommendationService;
    /**
     * Find all recommended responses for user
     *
     * @param user the user
     * @return the response list
     */
    List<InteractionResponse> findRecommendedResponsesForUser(User user, int attempt =1) {
        Interaction interaction = this.responseSubmissionInteraction;
        def res = []
        if (this.executionIsFaceToFace()) {
            InteractionResponse userResponse = InteractionResponse.findByInteractionAndLearnerAndAttempt(interaction, user, attempt)
            if (userResponse) {
                res = interaction.explanationRecommendationMap()[userResponse.id as String].collect {
                    InteractionResponse.get(it)
                }
            }
        } else {
            def limit = EvaluationSpecification.MAX_RESPONSE_TO_EVALUATE_COUNT
            res = responseRecommendationService.findAllResponsesOrderedByEvaluationCount(interaction, 2, limit)
        }
        return res;
    }

    /**
     * Find all good responses with explanations
     *
     * @return the good responses
     */
    List<InteractionResponse> findAllGoodResponses(int attempt =1) {
        Interaction interaction = responseSubmissionInteraction
        InteractionResponse.findAllByInteractionAndAttemptAndScore(interaction, attempt, 100f,
                [sort:"meanGrade", order:"desc"])
    }

    /**
     * Find all open responses with explanations
     *
     * @return the open responses
     */
    List<InteractionResponse> findAllOpenResponses(int attempt =1) {
        Interaction interaction = responseSubmissionInteraction
        def res = InteractionResponse.findAllByInteractionAndAttempt(interaction, attempt,
                [sort:"meanGrade", order:"desc"])
        res
    }

    /**
     * @return true if the sequence has explanations
     */
    boolean hasExplanations() {
        responseSubmissionSpecification ?.studentsProvideExplanation
    }

    // TODO Question
    /**
     * Find or create learner sequence
     *
     * @param learner the learner
     */
    private LearnerSequence findOrCreateLearnerSequence(User learner) {
        LearnerSequence ls = LearnerSequence.findByLearnerAndSequence(learner, this)
        if (!ls) {
            ls = new LearnerSequence(learner:learner, sequence:this)
            if (activeInteraction) {
                ls.activeInteraction = responseSubmissionInteraction
            }
            ls.save();
        }
        if (!ls.activeInteraction && activeInteraction) {
            ls.activeInteraction = responseSubmissionInteraction
            ls.save();
        }
        ls
    }


}

enum StateType {
    beforeStart,
    show,
    afterStop;
}
