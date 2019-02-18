package org.elaastic.qtapi.Services;

import org.elaastic.qtapi.entities.Interaction;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InteractionService {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {

        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public List<Interaction> findAllInteraction() {

        String query = "SELECT m FROM Interaction m ORDER BY m.interactionType" ;
        TypedQuery<Interaction> queryObj = entityManager.createQuery(query, Interaction.class);
        return queryObj.getResultList();
    }

}
