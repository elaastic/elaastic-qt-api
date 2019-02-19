package org.elaastic.qtapi.Services;

import org.elaastic.qtapi.entities.Sequence;
import org.elaastic.qtapi.entities.Statement;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StatementService {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {

        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public List<Statement> findAllStatement() {

        String query = "SELECT m FROM Statement m ORDER BY m.title" ;
        TypedQuery<Statement> queryObj = entityManager.createQuery(query, Statement.class);
        return queryObj.getResultList();
    }

}
