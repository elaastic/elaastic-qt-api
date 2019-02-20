package org.elaastic.qtapi.IntegrationTest;

import org.elaastic.qtapi.entities.Assignment;
import org.elaastic.qtapi.entities.Sequence;
import org.elaastic.qtapi.entities.Statement;
import org.elaastic.qtapi.entities.User;
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

    @Before
    public void setUp() {

        users = setUpUser();
        assignments = setUpAssignement();
        sequences = setUpSequence();
        statements = setUpStatement();


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
        stat1.setContent("<p>Cochez les assertions vraies :</p> " +
                "<ol> " +
                "<li>Git repose sur une architecture centralisée</li>" +
                "<li>Avec Git, chaque développeur possède une copie du repository</li>" +
                "<li>Le projet Git a été initié par Linus Thorvald pour les besoins du développement du noyau Linux</li>" +
                "<li>Git n'est utilisable que sur les systèmes de type Unix </li> <li>Avec Git, il n'est plus possible d'avoir un repository partagé de référence</li>" +
                "<li>Git est leader dans la catégorie des outils de gestion distribuée de version de code</li></ol>");
        stat1.setOwner(entitiesServices.findUserById(359));
        stat1.setQuestionType(QuestionType.MultipleChoice);
        stat1.setChoiceSpecification("{\"expectedChoiceList\":[{\"index\":2,\"score\":33.333332},{\"index\":3,\"score\":33.333332},{\"index\":6,\"score\":33.333332}],\"choiceInteractionType\":\"MULTIPLE\",\"itemCount\":6}");
        stat1.setParentStatement(null);
        stat1.setExpectedExplanation("");

        statements.add(stat1);


        stat2 = new Statement();
        stat2.setId(619);

        dateC1 = null;
        dateLU1 = null;
        try {
            dateC1 = formatter.parse("2017-10-09 17:26:16");
            dateLU1 = formatter.parse("2017-10-09 17:26:16");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        stat2.setDateCreated(dateC1);
        stat2.setLastUpdated(dateLU1);
        stat2.setTitle("Git - Aire d'embarquement");
        // can be a probleme
        stat2.setContent("<p>L\'aire d'embarquement est le &quot;passage obligé&quot; pour un fichier que l\'on souhaite ajouter au repository.</p>" +
                        "<ol>" +
                        "<li>Vrai</li>" +
                        "<li>Faux</li>" +
                        "</ol>");

        stat2.setOwner(entitiesServices.findUserById(359));
        stat2.setQuestionType(QuestionType.ExclusiveChoice);
        stat2.setChoiceSpecification("{\"expectedChoiceList\":[{\"index\":1,\"score\":100.0}],\"choiceInteractionType\":\"EXCLUSIVE\",\"itemCount\":2}");
        stat2.setParentStatement(null);
        stat2.setExpectedExplanation("");

        statements.add(stat2);

        return statements;
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

    @Test
    public void testFindAllAssignement(){

        List<Assignment> allAssignement = entitiesServices.findAllAssignments();

        assignments = new ArrayList<Assignment>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        assert(allAssignement.size() == 1);

        assert(listContainTest(assignments, allAssignement));

    }

    @Test
    public void findAssignmentByIdTest() {

        assert(assignments.get(0).equals(entitiesServices.findUserById(assignments.get(0).getId())));

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
    public void testfindAllUser() {

        List<User> fetchUser = entitiesServices.findAllUser();
        // Assert that the full name is in the List

        assert(listContainTest(users, fetchUser));

        // Test list size
        assert(fetchUser.size() == 11);
    }

    @Test
    public void testfindAllStatement() {

        List<Statement> fetchStatements = entitiesServices.findAllStatement();
        // Assert that the full name is in the List

        assert(listContainTest(statements, fetchStatements));

        // Test list size
        assert(fetchStatements.size() == 5);
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
    public void testFindAllSequence(){

        List<Sequence> allSequence = entitiesServices.findAllSequence();

        assert(allSequence.size() == 5);

        assert(listContainTest(sequences, allSequence));
    }

    /**
     * Initialise a list of Assignement
     * @return users A list of Assignement
     */
    private ArrayList<Assignment> setUpAssignement() {

        ArrayList<Assignment> assignements = new ArrayList<>();

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

        return assignements;
    }

    /**
     * Initialise a list of Assignement
     * @return users A list of Assignement
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
        sequence1.setActiveInteraction(entitiesServices.findInteractionById(1714));
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
        sequence2.setActiveInteraction(entitiesServices.findInteractionById(1690));
        sequence2.setState("show");

        sequences.add(sequence1);
        sequences.add(sequence2);

        return sequences;
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

}
