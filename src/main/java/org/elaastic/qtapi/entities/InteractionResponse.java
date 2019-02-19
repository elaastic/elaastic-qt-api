package org.elaastic.qtapi.entities;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "choice_interaction_response")
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

    /**
     * Get the choice list
     * @return the choice list
     */
    ArrayList<Integer>choiceList() {
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

}

