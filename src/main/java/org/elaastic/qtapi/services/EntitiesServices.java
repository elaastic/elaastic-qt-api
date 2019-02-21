package org.elaastic.qtapi.services;

import org.elaastic.qtapi.entities.*;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EntitiesServices {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Assignment> findAllAssignments() {

        String query = "SELECT m FROM Assignment m ORDER BY m.title" ;
        TypedQuery<Assignment> queryObj = entityManager.createQuery(query, Assignment.class);
        return queryObj.getResultList();
    }

    public List<Attachement> findAllAttachement() {

        String query = "SELECT m FROM Attachement m ORDER BY m.name" ;
        TypedQuery<Attachement> queryObj = entityManager.createQuery(query, Attachement.class);
        return queryObj.getResultList();
    }

    public List<Interaction> findAllInteraction() {

        String query = "SELECT m FROM Interaction m ORDER BY m.interactionType" ;
        TypedQuery<Interaction> queryObj = entityManager.createQuery(query, Interaction.class);
        return queryObj.getResultList();
    }

    public List<InteractionResponse> findAllInteractionResponse() {

        String query = "SELECT m FROM InteractionResponse m ORDER BY m.interactionType" ;
        TypedQuery<InteractionResponse> queryObj = entityManager.createQuery(query, InteractionResponse.class);
        return queryObj.getResultList();
    }

//
//    public List<LearnerSequence> findAllLearnerSequence() {
//
//        String query = "SELECT m FROM LearnerSequence m ORDER BY m.learner" ;
//        TypedQuery<LearnerSequence> queryObj = entityManager.createQuery(query, LearnerSequence.class);
//        return queryObj.getResultList();
//    }
//
//    public List<PeerGrading> findAllPeerGrading() {
//
//        String query = "SELECT m FROM PeerGrading m ORDER BY m.dateCreated" ;
//        TypedQuery<PeerGrading> queryObj = entityManager.createQuery(query, PeerGrading.class);
//        return queryObj.getResultList();
//    }
//
    public List<Sequence> findAllSequence() {

        String query = "SELECT m FROM Sequence m ORDER BY m.dateCreated" ;
        TypedQuery<Sequence> queryObj = entityManager.createQuery(query, Sequence.class);
        return queryObj.getResultList();
    }

    public List<Statement> findAllStatement() {

        String query = "SELECT m FROM Statement m ORDER BY m.title" ;
        TypedQuery<Statement> queryObj = entityManager.createQuery(query, Statement.class);
        return queryObj.getResultList();
    }

    public List<User> findAllUser() {

        String query = "SELECT u FROM User u ORDER BY u.firstName" ;
        TypedQuery<User> queryObj = entityManager.createQuery(query, User.class);
        return queryObj.getResultList();
    }

    public User findUserById(long id) {
        return entityManager.find(User.class, id);
    }

    public Assignment findAssignmentById(long id) {
        return entityManager.find(Assignment.class, id);
    }

    public Statement findStatementById(long id) {
        return entityManager.find(Statement.class, id);
    }

    public Interaction findInteractionById(long id) {
        return entityManager.find(Interaction.class, id);
    }

    public Sequence findSequenceById(long id) {
        return entityManager.find(Sequence.class, id);
    }

}
