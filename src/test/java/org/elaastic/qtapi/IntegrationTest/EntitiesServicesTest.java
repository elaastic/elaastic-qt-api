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
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EntitiesServicesTest {

    @Autowired
    private EntitiesServices entitiesServices;

    private User user;

    @Before
    public void setUp() {

        // given a a valid Enterprise
        user = new User();

        System.out.println("coucou");

    }

    @Test
    public void testfindAllAssignments() {
        List<User> fetchProject = entitiesServices.findAllUser();
    }
}
