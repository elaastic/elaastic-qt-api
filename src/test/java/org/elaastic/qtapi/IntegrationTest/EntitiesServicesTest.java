package org.elaastic.qtapi.IntegrationTest;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.elaastic.qtapi.entities.*;
import org.elaastic.qtapi.enumeration.InteractionType;
import org.elaastic.qtapi.enumeration.QuestionType;

import org.elaastic.qtapi.services.EntitiesServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EntitiesServicesTest{

    @Autowired
    private EntitiesServices entitiesServices;
  
    private ArrayList<User> users;

    private ArrayList<Assignment> assignments;

    private ArrayList<Sequence> sequences;

    private ArrayList<Statement> statements;

    private ArrayList<Interaction> interactions;

    private ArrayList<InteractionResponse> interactionResponses;

    private ArrayList<Attachement> attachements;

    private ArrayList<PeerGrading> peerGradings;

    private ArrayList<LearnerSequence> learnerSequences;

    @Before
    public void setUp() {

        users = setUpUser();
        assignments = setUpAssignment();
        sequences = setUpSequence();
        statements = setUpStatement();
        interactions = setUpInteraction();
        interactionResponses = setUpInteractionResponse();
        attachements = setUpAttachement();
        peerGradings = setUpPeerGrading();
        learnerSequences = setUpLearnerSequence();
    }

    @Test
    public void testFindAllLearnerSequence() {
        List<LearnerSequence> fetchLearnerSequence = entitiesServices.findAllLearnerSequence();

        assert(fetchLearnerSequence.size() == 2);

        assert(listContainTest(learnerSequences, fetchLearnerSequence));
    }

    @Test
    public void testFindAllPeerGrading() {
        List<PeerGrading> fetchPeerGrading = entitiesServices.findAllPeerGrading();

        assert(fetchPeerGrading.size() == 2);

        assert(listContainTest(peerGradings, fetchPeerGrading));
    }

    @Test
    public void testFindAllAttachement(){

        List<Attachement> fetchAttachement = entitiesServices.findAllAttachement();

        assert(fetchAttachement.size() == 2);

        assert(listContainTest(attachements, fetchAttachement));

    }

    @Test
    public void testFindAllInteractionResponse(){

        List<InteractionResponse> fetchInteractionResponse = entitiesServices.findAllInteractionResponse();

        assert(fetchInteractionResponse.size() == 8);

        assert(listContainTest(interactionResponses, fetchInteractionResponse));

    }

    @Test
    public void testFindAllInteraction(){

        List<Interaction> fetchInteraction = entitiesServices.findAllInteraction();

        assert(fetchInteraction.size() == 2);

        assert(listContainTest(interactions, fetchInteraction));

    }

    @Test
    public void testFindAllAssignement(){

        List<Assignment> fetchAssignement = entitiesServices.findAllAssignments();

        assert(fetchAssignement.size() == 1);

        assert(listContainTest(assignments, fetchAssignement));

    }

    @Test
    public void testfindAllUser() {

        List<User> fetchUser = entitiesServices.findAllUser();

        // Test list size
        assert(fetchUser.size() == 12);

        // Assert that the full name is in the List
        assert(listContainTest(users, fetchUser));


    }

    @Test
    public void testfindAllStatement() {

        List<Statement> fetchStatements = entitiesServices.findAllStatement();

        // Test list size
        assert(fetchStatements.size() == 5);

        // Assert that the full name is in the List
        assert(listContainTest(statements, fetchStatements));


    }

    @Test
    public void testFindAllSequence(){

        List<Sequence> fetchSequence = entitiesServices.findAllSequence();

        assert(fetchSequence.size() == 2);

        assert(listContainTest(sequences, fetchSequence));
    }

    @Test
    public void findInteractionResponseByIdTest() {

        assert(interactionResponses.get(0).equals(entitiesServices.findInteractionResponseById(interactionResponses.get(0).getId())));
    }

    @Test
    public void findUserByIdTest() {

        User user1 = users.get(0),
                user2 = users.get(users.size()-1);

        User fetchUser1 = entitiesServices.findUserById(user1.getId());

        assert(user1.equals(fetchUser1));

        User fetchUser2 = entitiesServices.findUserById(user2.getId());

        assert(user2.equals(fetchUser2));
    }

    @Test
    public void findAssignmentByIdTest() {

        assert(assignments.get(0).equals(entitiesServices.findAssignmentById(assignments.get(0).getId())));

    }

    @Test
    public void findStatementByIdTest() {

        assert(statements.get(0).equals(entitiesServices.findStatementById(statements.get(0).getId())));

    }

    @Test
    public void findInteractionByIdTest() {

        assert(interactions.get(0).equals(entitiesServices.findInteractionById(interactions.get(0).getId())));

    }

    @Test
    public void findSequenceByIdTest() {

        assert(sequences.get(0).equals(entitiesServices.findSequenceById(sequences.get(0).getId())));

    }

    /**
     * Methode permettant de voir si le contenu d'une liste existe dans une autre liste;
     * @param contentsList liste contenant les objets à tester
     * @param containingList liste dans laquelle on test les objets
     * @return true si tout c'est bien passé false si le test ne c'est pas bien passé
     */
    public boolean listContainTest(ArrayList<?> contentsList, List<?> containingList){

        for (Object obj: contentsList){
            if(!containingList.contains(obj)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Initialise a list of User
     * @return A list of User
     */
    private ArrayList<User> setUpUser() {

        User user1, user2, user3;

        // id, email, first_name, last_name, normalized_username, password, username, owner_id, can_be_user_owner
        ArrayList<User> users = new ArrayList<>();
        user1 = new User();
        user1.setId(211);
        user1.setEmail("admin@tsaap.org");
        user1.setFirstName("Admin");
        user1.setLastName("Tsaap");
        user1.setNormalizedUsername("admin");
        user1.setPassword("7110eda4d09e062aa5e4a390b0a572ac0d2c0220");
        user1.setUsername("admin");
        user1.setOwner(null);
        user1.setCanBeUserOwner((byte)0);
        // add the user
        users.add(user1);

        user2 = new User();
        user2.setId(300);
        user2.setEmail("admin2@tsaap.org");
        user2.setFirstName("Admin2");
        user2.setLastName("Tsaap2");
        user2.setNormalizedUsername("admin2");
        user2.setPassword("7110eda4d09e062aa5e4a390b0a572ac0d2c0220");
        user2.setUsername("admin2");
        user2.setOwner(null);
        user2.setCanBeUserOwner((byte)1);
        // add the user
        users.add(user2);

        user3 = new User();
        user3.setId(4946);
        user3.setEmail("John_Doe___9@fakeuser.com");
        user3.setFirstName("John");
        user3.setLastName("Doe");
        user3.setNormalizedUsername("john_doe___9");
        user3.setPassword("06c129f13e5ec40f6d08f57504d30cf416e6cad9");
        user3.setUsername("John_Doe___9");
        user3.setOwner(null);
        user3.setCanBeUserOwner((byte)0);
        // add the user
        users.add(user3);


        return users;
    }

    /**
     * Initialise a list of Assignement
     * @return users A list of assignement
     */
    private ArrayList<Assignment> setUpAssignment() {

        ArrayList<Assignment> assignments = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date dateC1 = null;
        Date dateLU1 = null;
        try {
            dateC1 = formatter.parse("2017-10-09 17:08:59");
            dateLU1 = formatter.parse("2017-10-12 07:51:36");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Assignment assign1 = new Assignment();
        assign1.setId(382);
        assign1.setTitle("EMINL1H1 : Développement collaboratif, qualité: Quizz Git");
        assign1.setOwner(entitiesServices.findUserById(359));
        assign1.setDateCreated(dateC1);
        assign1.setLastUpdated(dateLU1);
        assign1.setGlobalId("c71b94b6-ad03-11e7-93a4-00163e3774aa");

        assignments.add(assign1);

        return assignments;
    }

    /**
     * Initialise a list of Sequence
     * @return users A list of sequence
     */
    private ArrayList<Sequence> setUpSequence() {

        ArrayList<Sequence> sequences = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date dateC1 = null;
        Date dateLU1 = null;
        try {

            dateC1 = formatter.parse("2017-10-09 17:23:58");
            dateLU1 = formatter.parse("2017-10-12 08:01:17");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Sequence sequence1 = new Sequence();

        sequence1.setId(611);
        sequence1.setRank(1);
        sequence1.setDateCreated(dateC1);
        sequence1.setLastUpdated(dateLU1);
        sequence1.setOwner(entitiesServices.findUserById(359));
        sequence1.setAssignment(entitiesServices.findAssignmentById(382));
        sequence1.setStatement(entitiesServices.findStatementById(618));
        sequence1.setActiveInteraction(entitiesServices.findInteractionById(1712));
        sequence1.setState("show");

        Date dateC2 = null;
        Date dateLU2 = null;
        try {

            dateC2 = formatter.parse("2017-10-09 17:38:16");
            dateLU2 = formatter.parse("2017-10-10 08:54:53");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Sequence sequence2 = new Sequence();

        sequence2.setId(615);
        sequence2.setRank(5);
        sequence2.setDateCreated(dateC2);
        sequence2.setLastUpdated(dateLU2);
        sequence2.setOwner(entitiesServices.findUserById(359));
        sequence2.setAssignment(entitiesServices.findAssignmentById(382));
        sequence2.setStatement(entitiesServices.findStatementById(622));
        sequence2.setActiveInteraction(entitiesServices.findInteractionById(1688));
        sequence2.setState("show");

        sequences.add(sequence1);
        sequences.add(sequence2);

        return sequences;
    }

    /**
     * Initialise a list of Statement
     * @returnA list of statement
     */
    private ArrayList<Statement> setUpStatement() {
        Statement stat1, stat2, stat3;

        ArrayList<Statement> statements = new ArrayList<>();

        Date dateC1;
        Date dateLU1;

        //id;date_created;last_updated;title;content;owner_id;question_type;choice_specification;parent_statement_id;expected_explanation;
        stat1 = new Statement();
        stat1.setId(618);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        dateC1 = null;
        dateLU1 = null;
        try {
            dateC1 = formatter.parse("2017-10-09 17:23:58");
            dateLU1 = formatter.parse("2017-10-09 17:23:58");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        stat1.setDateCreated(dateC1);
        stat1.setLastUpdated(dateLU1);
        stat1.setTitle("Git - Concepts clés");
        // can be a problem
        stat1.setContent("<p>Cochez les assertions vraies :</p>" +
                "<ol> " +
                "<li>Git repose sur une architecture centralisée</li>  " +
                "<li>Avec Git, chaque développeur possède une copie du repository</li>  " +
                "<li>Le projet Git a été initié par Linus Thorvald pour les besoins du développement du noyau Linux</li>" +
                "<li>Git n'est utilisable que sur les systèmes de type Unix </li> <li>Avec Git, il n'est plus possible d'avoir un repository partagé de référence</li>" +
                "<li>Git est leader dans la catégorie des outils de gestion distribuée de version de code</li></ol>");
        stat1.setOwner(entitiesServices.findUserById(359));
        stat1.setQuestionType(QuestionType.MultipleChoice.toString());
        stat1.setChoiceSpecification("{\"expectedChoiceList\":[{\"index\":2,\"score\":33.333332},{\"index\":3,\"score\":33.333332},{\"index\":6,\"score\":33.333332}],\"choiceInteractionType\":\"MULTIPLE\",\"itemCount\":6}");
        stat1.setParentStatement(null);
        stat1.setExpectedExplanation("");

        statements.add(stat1);


        stat2 = new Statement();
        stat2.setId(619);

        Date dateC2;
        Date dateLU2;

        dateC2 = null;
        dateLU2 = null;
        try {
            dateC2 = formatter.parse("2017-10-09 17:26:16");
            dateLU2 = formatter.parse("2017-10-09 17:26:16");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        stat2.setDateCreated(dateC2);
        stat2.setLastUpdated(dateLU2);
        stat2.setTitle("Git - Aire d'embarquement");
        // can be a probleme
        stat2.setContent("<p>L\'aire d'embarquement est le &quot;passage obligé&quot; pour un fichier que l\'on souhaite ajouter au repository.</p>" +
                "<ol>" +
                "<li>Vrai</li>" +
                "<li>Faux</li>" +
                "</ol>");
        stat2.setOwner(entitiesServices.findUserById(359));
        stat2.setQuestionType(QuestionType.ExclusiveChoice.toString());
        stat2.setChoiceSpecification("{\"expectedChoiceList\":[{\"index\":1,\"score\":100.0}],\"choiceInteractionType\":\"EXCLUSIVE\",\"itemCount\":2}");
        stat2.setParentStatement(null);
        stat2.setExpectedExplanation("");

        statements.add(stat2);

        return statements;
    }

    /**
     * Initialise a list of Interaction
     * @returnA list of interaction
     */
    private ArrayList<Interaction> setUpInteraction(){

        ArrayList<Interaction> interactions = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date dateC1 = null;
        Date dateLU1 = null;
        try {
            dateC1 = formatter.parse("2017-10-12 07:51:33");
            dateLU1 = formatter.parse("2017-10-12 07:57:52");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Interaction interaction1 = new Interaction();

        interaction1.setId(1712);
        interaction1.setRank(1);
        interaction1.setDateCreated(dateC1);
        interaction1.setLastUpdated(dateLU1);
        interaction1.setOwner(entitiesServices.findUserById(359));
        interaction1.setInteractionType(InteractionType.ResponseSubmission.toString());
        interaction1.setSpecification("{\"studentsProvideExplanation\":false,\"studentsProvideConfidenceDegree\":false}");
        interaction1.setSequence(entitiesServices.findSequenceById(611));
        interaction1.setResults("{\"1\":[0.000,29.167,87.500,58.333,0.000,8.333,87.500]}");
        interaction1.setExplanationRecommendationMapping(null);
        interaction1.setState("afterStop");

        Date dateC2 = null;
        Date dateLU2 = null;
        try {

            dateC2 = formatter.parse("2017-10-10 08:54:53");
            dateLU2 = formatter.parse("2017-12-13 21:08:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Interaction interaction2 = new Interaction();

        interaction2.setId(1688);
        interaction2.setRank(1);
        interaction2.setDateCreated(dateC2);
        interaction2.setLastUpdated(dateLU2);
        interaction2.setOwner(entitiesServices.findUserById(359));
        interaction2.setInteractionType(InteractionType.ResponseSubmission.toString());
        interaction2.setSpecification("{\"studentsProvideExplanation\":true,\"studentsProvideConfidenceDegree\":true}");
        interaction2.setSequence(entitiesServices.findSequenceById(615));
        interaction2.setResults("{\"1\":[0.000,100.000,0.000],\"2\":[0.000,93.939,6.061]}");
        interaction2.setExplanationRecommendationMapping(null);
        interaction2.setState("beforeStart");

        interactions.add(interaction1);
        interactions.add(interaction2);

        return interactions;
    }

    /**
     * Initialise a list of InteractionResponse
     * @returnA list of interactionResponse
     */
    private ArrayList<InteractionResponse> setUpInteractionResponse(){

        ArrayList<InteractionResponse> interactions = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date dateC1 = null;
        Date dateLU1 = null;
        try {
            dateC1 = formatter.parse("2017-10-12 07:52:29");
            dateLU1 = formatter.parse("2017-10-12 07:52:29");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        InteractionResponse interactionResp1 = new InteractionResponse();

        interactionResp1.setId(7893);
        interactionResp1.setDateCreated(dateC1);
        interactionResp1.setLastUpdated(dateLU1);
        interactionResp1.setLearner(entitiesServices.findUserById(359));
        interactionResp1.setInteraction(entitiesServices.findInteractionById(1712));
        interactionResp1.setChoiceListSpecification("[2,3,6]");
        interactionResp1.setExplanation(null);
        interactionResp1.setConfidenceDegree(null);
        interactionResp1.setScore(100f);
        interactionResp1.setAttempt(1);
        interactionResp1.setMeanGrade(null);

        Date dateC2 = null;
        Date dateLU2 = null;
        try {

            dateC2 = formatter.parse("2017-12-13 21:07:04");
            dateLU2 = formatter.parse("2017-12-13 21:07:04");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        InteractionResponse interactionResp2 = new InteractionResponse();

        interactionResp2.setId(11777);
        interactionResp2.setDateCreated(dateC2);
        interactionResp2.setLastUpdated(dateLU2);
        interactionResp2.setLearner(entitiesServices.findUserById(359));
        interactionResp2.setInteraction(entitiesServices.findInteractionById(1688));
        interactionResp2.setChoiceListSpecification("[2]");
        interactionResp2.setExplanation("<p>La plupart du temps il est utilie de partager le .gitignore avec les autres développeur</p>");
        interactionResp2.setConfidenceDegree(0);
        interactionResp2.setScore(0f);
        interactionResp2.setAttempt(1);
        interactionResp2.setMeanGrade(null);

        interactions.add(interactionResp1);
        interactions.add(interactionResp2);

        return interactions;
    }

    /**
     * Initialise a list of Attachement
     * @return A list of attachement
     */
    private ArrayList<Attachement> setUpAttachement(){

        ArrayList<Attachement> attachements = new ArrayList<>();

        Attachement attachement1 = new Attachement();

        attachement1.setId(1);
        attachement1.setPath("C:/testPath/test.png");
        attachement1.setName("test");
        attachement1.setOriginalName(null);
        attachement1.setSize(null);
        attachement1.setDimensionHeight(null);
        attachement1.setDimensionWidth(null);
        attachement1.setTypeMime(null);
        attachement1.setStatement(null);
        attachement1.setToDelete(null);

        Attachement attachement2 = new Attachement();

        attachement2.setId(2);
        attachement2.setPath("C:/testPath/test2.png");
        attachement2.setName("test2");
        attachement2.setOriginalName(null);
        attachement2.setSize(null);
        attachement2.setDimensionHeight(null);
        attachement2.setDimensionWidth(null);
        attachement2.setTypeMime(null);
        attachement2.setStatement(null);
        attachement2.setToDelete(null);

        attachements.add(attachement1);
        attachements.add(attachement2);

        return attachements;
    }

    private ArrayList<PeerGrading> setUpPeerGrading() {

        PeerGrading grade1, grade2;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date dateC1 = null;
        Date dateLU1 = null;
        try {
            dateC1 = formatter.parse("2017-12-13 20:27:20");
            dateLU1 = formatter.parse("2017-12-13 20:27:20");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayList<PeerGrading> peerGradings = new ArrayList<>();
        grade1 = new PeerGrading();
        grade1.setId(15);
        grade1.setDateCreated(dateC1);
        grade1.setLastUpdated(dateLU1);
        grade1.setGrader(entitiesServices.findUserById(359));
        grade1.setGrade(4.5f);
        grade1.setAnnotation("peergrading1");
        grade1.setResponse(entitiesServices.findInteractionResponseById(7893));

        // add the peerGradings
        peerGradings.add(grade1);

        Date dateC2 = null;
        Date dateLU2 = null;
        try {
            dateC2 = formatter.parse("2017-12-13 21:07:04");
            dateLU2 = formatter.parse("2017-12-13 21:07:04");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        grade2 = new PeerGrading();
        grade2.setId(16);
        grade2.setDateCreated(dateC2);
        grade2.setLastUpdated(dateLU2);
        grade2.setGrader(entitiesServices.findUserById(359));
        grade2.setGrade(5.0f);
        grade2.setAnnotation("peergrading2");
        grade2.setResponse(entitiesServices.findInteractionResponseById(11777));

        // add the peerGradings
        peerGradings.add(grade2);

        return peerGradings;
    }

    private ArrayList<LearnerSequence> setUpLearnerSequence() {

        ArrayList<LearnerSequence> learnerSequences = new ArrayList<>();

        LearnerSequence learnerSequence1 = new LearnerSequence();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date dateC1 = null;
        Date dateLU1 = null;
        try {
            dateC1 = formatter.parse("2017-10-12 07:52:29");
            dateLU1 = formatter.parse("2017-10-12 07:52:29");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        learnerSequence1.setId(1);
        learnerSequence1.setLearner(entitiesServices.findUserById(359));
        learnerSequence1.setSequence(entitiesServices.findSequenceById(611));
        learnerSequence1.setActiveInteraction(entitiesServices.findInteractionById(1712));
        learnerSequence1.setDateCreated(dateC1);
        learnerSequence1.setLastUpdated(dateLU1);

        LearnerSequence learnerSequence2 = new LearnerSequence();

        Date dateC2 = null;
        Date dateLU2 = null;
        try {
            dateC2 = formatter.parse("2017-10-11 14:09:53");
            dateLU2 = formatter.parse("2017-10-12 06:44:51");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        learnerSequence2.setId(2);
        learnerSequence2.setLearner(entitiesServices.findUserById(359));
        learnerSequence2.setSequence(entitiesServices.findSequenceById(615));
        learnerSequence2.setActiveInteraction(entitiesServices.findInteractionById(1688));
        learnerSequence2.setDateCreated(dateC2);
        learnerSequence2.setLastUpdated(dateLU2);

        learnerSequences.add(learnerSequence1);
        learnerSequences.add(learnerSequence2);

        return learnerSequences;
    }

}