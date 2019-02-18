package org.elaastic.qtapi.IntegrationTest;

import org.elaastic.qtapi.Services.StatementService;
import org.elaastic.qtapi.Services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
public class UserIntegrationTest {

        private UserService userService;

        @MockBean
        private EntityManager entityManager;

        @Before
        public void setUp() throws Exception {
            userService = new UserService();
            userService.setEntityManager(entityManager);
        }

        @Test
        public void testFindAllAttachement() {
            // when: trying to find all assignment
            userService.findAllUser();
        }

}
