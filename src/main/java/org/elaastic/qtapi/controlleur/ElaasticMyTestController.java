package org.elaastic.qtapi.controlleur;

import org.elaastic.qtapi.entities.Sequence;
import org.elaastic.qtapi.services.EntitiesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ElaasticMyTestController {

    private EntitiesServices entitiesServices;

    /**
     * @param entitiesServices
     */
    public ElaasticMyTestController(@Autowired EntitiesServices entitiesServices) {
        this.entitiesServices = entitiesServices;
    }

    @RequestMapping("/questions")
    public List<Sequence> findAllSequence() {
        return entitiesServices.findAllSequence();
    }
}