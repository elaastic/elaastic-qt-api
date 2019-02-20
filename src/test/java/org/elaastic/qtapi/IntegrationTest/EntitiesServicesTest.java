package org.elaastic.qtapi.IntegrationTest;

import org.elaastic.qtapi.entities.Assignment;
import org.elaastic.qtapi.entities.User;
import org.elaastic.qtapi.services.EntitiesServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EntitiesServicesTest {

    @Autowired
    private EntitiesServices entitiesServices;

    private ArrayList<Assignment> assignments;

    @Before
    public void setUp() {



        System.out.println("coucou");

    }

    @Test
    public void testFindAllAssignement(){

        List<Assignment> allAssignement = entitiesServices.findAllAssignments();
        assignments = new ArrayList<Assignment>();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

        Date dateC1 = null;
        Date dateLU1 = null;
        try {
            dateC1 = formatter.parse("2017-10-09 17:08:59");
            dateLU1 = formatter.parse("2017-10-12 07:51:36");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Assignment assign1 = new Assignment();
        assign1.setId(382);
        assign1.setTitle("EMINL1H1 : Développement collaboratif, qualité: Quizz Git");
        assign1.setOwner(entitiesServices.findUserById(359));
        assign1.setDateCreated(dateC1);
        assign1.setLastUpdated(dateLU1);
        assign1.setGlobalId("c71b94b6-ad03-11e7-93a4-00163e3774aa");
    }

    /**
     * Methode permettant de voir si le contenu d'une liste existe dans une autre liste;
     * @param contentsList liste contenant les objets à tester
     * @param containingList liste dans laquelle on test les objets
     * @return true si tout c'est bien passé false si le test ne c'est pas bien passé
     */
    public boolean listContainTest(List<Object> contentsList, List<Object> containingList){

        for (Object obj: contentsList){
            if(!containingList.contains(obj)) {
                return false;
            }
        }
        return true;
    }

}
