package org.elaastic.qtapi.IntegrationTest;

import org.elaastic.qtapi.Services.InteractionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
public class InteractionIntegrationTest {

    private InteractionService interactionService;

    @MockBean
    private EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        interactionService = new InteractionService();
        interactionService.setEntityManager(entityManager);
    }

    @Test
    public void testFindAllAttachement() {
        // when: trying to find all assignment
        interactionService.findAllInteraction();
    }

}
