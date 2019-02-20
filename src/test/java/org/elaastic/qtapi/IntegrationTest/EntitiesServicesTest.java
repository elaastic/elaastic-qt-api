package org.elaastic.qtapi.IntegrationTest;

import org.elaastic.qtapi.entities.User;
import org.elaastic.qtapi.services.EntitiesServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EntitiesServicesTest {

    @Autowired
    private EntitiesServices entitiesServices;

    private ArrayList<User> users;

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
}
