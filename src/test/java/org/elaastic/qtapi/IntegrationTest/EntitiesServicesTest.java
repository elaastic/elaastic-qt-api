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

        User user1, user2, user3, user4, user5, user6, user7, user8, user9, user10;

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
        user1.setCanBeUserOwner(false);

        users.add(user1);

        return users;
    }

    @Test
    public void testfindAllUser() {
        List<User> fetchProject = entitiesServices.findAllUser();

        // Assert that the full name is in the List
        for (User user : users) {
            System.out.println(user);
            System.out.println(fetchProject.get(0));
            assert(fetchProject.contains(user));
        }
    }
}
