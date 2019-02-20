package org.elaastic.qtapi.IntegrationTest;

import org.elaastic.qtapi.entities.Assignment;
import org.elaastic.qtapi.entities.User;
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

    @Before
    public void setUp() {

        users = setUpUser();
    }

    /**
     * Initialise a list of User
     * @return users A list of User
     */
    private ArrayList<User> setUpUser() {

        User user1, user2, user3, user4, user5;

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

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

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

        listContainTest(assignments, allAssignement);
    }
  
    public void testfindAllUser() {

        List<User> fetchUser = entitiesServices.findAllUser();
        // Assert that the full name is in the List
        for (User user : users) {
            assert(fetchUser.contains(user));
        }

        // Test list size
        assert(fetchUser.size() == 11);
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
