package org.elaastic.qtapi.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;

public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String title;
    @NotBlank
    private String content;

    @NotNull
    private String expectedExplanation;
    @NotNull
    private String choiceSpecification;
    @NotNull
    private QuestionType questionType;

    private Statement parentStatement;


    private Date dateCreated;
    private Date lastUpdated;

    @NotNull
    private User owner;

    private Attachement attachement;

//    static constraints = {
//        questionType nullable: false
//        choiceSpecification validator: { val, obj ->
//            if ((obj.questionType == QuestionType.MultipleChoice || obj.questionType == QuestionType.ExclusiveChoice)
//                    && !val) return ['choiceSpecificationMustBeSet']
//        }
//    }

//    static transients = ['choiceSpecificationObject', 'fakeExplanations']


    // TODO what is this ? and this type
    /**
     * Get the choice specification object
     * @return the choice specification
     */
    ChoiceSpecification getChoiceSpecificationObject() {
        def res = null
        if (choiceSpecification) {
            res = new ChoiceSpecification(choiceSpecification);
        }
        return res;
    }

    /**
     *
     * @return true if statement describes an open-ended question
     */
    boolean isOpenEnded() {
        return questionType == QuestionType.OpenEnded;
    }

    /**
     *
     * @return true if statement describes a multiple choice question
     */
    boolean isMultipleChoice() {
        return questionType == QuestionType.MultipleChoice;
    }

    /**
     *
     * @return true if statement describes an exclusive choice question
     */
    boolean isExclusiveChoice() {
        return questionType == QuestionType.ExclusiveChoice;
    }

    /**
     * Get the attachment
     * @return the attachment
     */
    public Attachement getAttachment() {
        return attachement;
    }
}

enum QuestionType {
    Undefined,
    ExclusiveChoice,
    MultipleChoice,
    OpenEnded
}
