package org.elaastic.qtapi.IntegrationTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
public class LearnerSequenceIntegrationTest {

    private LearnerSequenceService learnerSequenceService;

    @MockBean
    private EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        learnerSequenceService = new LearnerSequenceService();
        learnerSequenceService.setEntityManager(entityManager);
    }

    @Test
    public void testFindAllAttachement() {
        // when: trying to find all assignment
        learnerSequenceService.findAllLearnerSequence();
    }

}
