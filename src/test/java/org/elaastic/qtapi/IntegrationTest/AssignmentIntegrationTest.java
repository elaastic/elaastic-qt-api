package org.elaastic.qtapi.IntegrationTest;

import org.elaastic.qtapi.Services.AssignmentService;
import org.elaastic.qtapi.entities.Assignment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
public class AssignmentIntegrationTest {

    private AssignmentService assignmentService;

    @MockBean
    private EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        assignmentService = new AssignmentService();
        assignmentService.setEntityManager(entityManager);
    }

    @Test
    public void testFindAllAssignment() {
        // when: trying to find all assignment
        assignmentService.findAllAssignments();
    }

}
