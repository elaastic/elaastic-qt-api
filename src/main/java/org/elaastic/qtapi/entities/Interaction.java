package org.elaastic.qtapi.entities;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Interaction {

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
    private Sequence sequence;
    @NotNull
    private String state;

    private String results; // Can be null
    private String explanationRecommendationMapping; // Can be null

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
    public Map<String, ArrayList<Long>> explanationRecommendationMap() {
        if (explanationRecommendationMapping == null) {
            return new HashMap<>();
        } else {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, ArrayList<Long>> res = new HashMap<String, ArrayList<Long>>();

            try {
                //JsonSlurper jsonSlurper = new JsonSlurper();
                res = mapper.readValue(explanationRecommendationMapping, new TypeReference<Map<String,  ArrayList<Long>>>(){});

            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return res;
        }
    }

    public static String getEmptySpecification() {
        return EMPTY_SPECIFICATION;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(String interactionType) {
        this.interactionType = interactionType;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
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

    public Sequence getSequence() {
        return sequence;
    }

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getExplanationRecommendationMapping() {
        return explanationRecommendationMapping;
    }

    public void setExplanationRecommendationMapping(String explanationRecommendationMapping) {
        this.explanationRecommendationMapping = explanationRecommendationMapping;
    }
}

enum InteractionType {
    ResponseSubmission,
    Evaluation,
    Read
}