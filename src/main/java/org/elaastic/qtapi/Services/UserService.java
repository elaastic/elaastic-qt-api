package org.elaastic.qtapi.Services;

import org.elaastic.qtapi.entities.Statement;
import org.elaastic.qtapi.entities.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {

        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public List<User> findAllUser() {

        String query = "SELECT m FROM User m ORDER BY m.firstName" ;
        TypedQuery<User> queryObj = entityManager.createQuery(query, User.class);
        return queryObj.getResultList();
    }

}
