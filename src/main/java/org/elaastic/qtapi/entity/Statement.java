package org.elaastic.qtapi.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;

@Entity
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

    /**
     * Get the choice specification object
     *
     * @return the choice specification
     */
    public ChoiceSpecification getChoiceSpecificationObject() {
        ChoiceSpecification res = null;
        if (choiceSpecification == null) {
            res = new ChoiceSpecification(choiceSpecification);
        }
        return res;
    }

    /**
     * @return true if statement describes an open-ended question
     */
    boolean isOpenEnded() {

        return questionType == QuestionType.OpenEnded;
    }

    /**
     * @return true if statement describes a multiple choice question
     */
    boolean isMultipleChoice() {

        return questionType == QuestionType.MultipleChoice;
    }

    /**
     * @return true if statement describes an exclusive choice question
     */
    boolean isExclusiveChoice() {

        return questionType == QuestionType.ExclusiveChoice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExpectedExplanation() {
        return expectedExplanation;
    }

    public void setExpectedExplanation(String expectedExplanation) {
        this.expectedExplanation = expectedExplanation;
    }

    public String getChoiceSpecification() {
        return choiceSpecification;
    }

    public void setChoiceSpecification(String choiceSpecification) {
        this.choiceSpecification = choiceSpecification;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public Statement getParentStatement() {
        return parentStatement;
    }

    public void setParentStatement(Statement parentStatement) {
        this.parentStatement = parentStatement;
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

    public Attachement getAttachement() {
        return attachement;
    }

    public void setAttachement(Attachement attachement) {
        this.attachement = attachement;
    }
}

enum QuestionType {
    Undefined,
    ExclusiveChoice,
    MultipleChoice,
    OpenEnded
}
