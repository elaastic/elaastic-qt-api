package org.elaastic.qtapi.IntegrationTest;

import org.elaastic.qtapi.Services.PeerGradingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
public class PeerGradingIntegrationTest {

    private PeerGradingService peerGradingService;

    @MockBean
    private EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        peerGradingService = new PeerGradingService();
        peerGradingService.setEntityManager(entityManager);
    }

    @Test
    public void testFindAllAttachement() {
        // when: trying to find all assignment
        peerGradingService.findAllPeerGrading();
    }

}
