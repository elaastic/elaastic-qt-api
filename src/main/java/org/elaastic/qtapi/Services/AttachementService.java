package org.elaastic.qtapi.Services;

import org.elaastic.qtapi.entities.Assignment;
import org.elaastic.qtapi.entities.Attachement;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class AttachementService {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {

        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public List<Attachement> findAllAttachement() {

        String query = "SELECT m FROM Attachement m ORDER BY m.name" ;
        TypedQuery<Attachement> queryObj = entityManager.createQuery(query, Attachement.class);
        return queryObj.getResultList();
    }
}
