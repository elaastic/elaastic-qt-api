package org.elaastic.qtapi.Services;

import org.elaastic.qtapi.entities.LearnerSequence;
import org.elaastic.qtapi.entities.PeerGrading;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PeerGradingService {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {

        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public List<PeerGrading> findAllPeerGrading() {

        String query = "SELECT m FROM PeerGrading m ORDER BY m.dateCreated" ;
        TypedQuery<PeerGrading> queryObj = entityManager.createQuery(query, PeerGrading.class);
        return queryObj.getResultList();
    }
}
