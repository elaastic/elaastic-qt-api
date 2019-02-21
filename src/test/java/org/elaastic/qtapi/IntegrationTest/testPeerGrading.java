package org.elaastic.qtapi.IntegrationTest;

import org.elaastic.qtapi.entities.*;
import org.elaastic.qtapi.services.EntitiesServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class testPeerGrading {

    @Autowired
    private EntitiesServices entitiesServices;

    private ArrayList<User> peerGradings;

    @Before
    public void setUp() {

        peerGradings = setUpPeerGrading();

    }

    private ArrayList<User> setUpPeerGrading() {

        PeerGrading grade1, grade2, grade3;

        ArrayList<PeerGrading> peerGradings = new ArrayList<>();
        grade1 = new PeerGrading();
        grade1.setId(15);
        grade1.setGrader(entitiesServices.findUserById(359));
        grade1.setGrade(4.5);
        grade1.setAnnotation("peergrading1");
//        grade1.setResponse(entitiesServices.findInteractionResponseById(7893));

        // add the user
        peerGradings.add(grade1);

        grade2 = new PeerGrading();
        grade2.setId(16);
        grade2.setGrader(entitiesServices.findUserById(359));
        grade2.setGrade(5.0);
        grade2.setAnnotation("peergrading2");
//        grade2.setResponse(entitiesServices.findInteractionResponseById(7784));

        // add the user
        peerGradings.add(grade1);

    }

    @Test
    public void testFindAllPeerGrading() {
        List<Interaction> fetchPeerGrading = entitiesServices.findAllInteraction();

        assert(fetchPeerGrading.size() == 2);

        assert(listContainTest(peerGradings, fetchPeerGrading));
    }

    /**
     * Methode permettant de voir si le contenu d'une liste existe dans une autre liste;
     * @param contentsList liste contenant les objets à tester
     * @param containingList liste dans laquelle on test les objets
     * @return true si tout c'est bien passé false si le test ne c'est pas bien passé
     */
    public boolean listContainTest(ArrayList<?> contentsList, List<?> containingList){

        for (Object obj: contentsList){
            if(!containingList.contains(obj)) {
                return false;
            }
        }
        return true;
    }

}
