package org.elaastic.qtapi.Services;

import org.elaastic.qtapi.entities.Interaction;
import org.elaastic.qtapi.entities.LearnerSequence;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LearnerSequenceService {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {

        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public List<LearnerSequence> findAllLearnerSequence() {

        String query = "SELECT m FROM LearnerSequence m ORDER BY m.learner" ;
        TypedQuery<LearnerSequence> queryObj = entityManager.createQuery(query, LearnerSequence.class);
        return queryObj.getResultList();
    }
}
