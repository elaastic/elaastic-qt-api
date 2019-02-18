package org.elaastic.qtapi.Services;

import org.elaastic.qtapi.entities.Attachement;
import org.elaastic.qtapi.entities.Sequence;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SequenceService {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {

        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public List<Sequence> findAllSequence() {

        String query = "SELECT m FROM Sequence m ORDER BY m.dateCreated" ;
        TypedQuery<Sequence> queryObj = entityManager.createQuery(query, Sequence.class);
        return queryObj.getResultList();
    }

}
