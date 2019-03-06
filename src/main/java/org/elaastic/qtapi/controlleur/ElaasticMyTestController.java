package org.elaastic.qtapi.controlleur;

import org.elaastic.qtapi.entities.Assignment;
import org.elaastic.qtapi.entities.Sequence;
import org.elaastic.qtapi.entities.Statement;
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

    @RequestMapping("/sequences")
    public List<Sequence> findAllSequence() {
        return entitiesServices.findAllSequence();
    }

    @RequestMapping("/statement")
    public List<Statement> findAllStatement() {
        return entitiesServices.findAllSequence();
    }

    @RequestMapping("/assigements")
    public List<Assignment> findAllAssigements() {
        return entitiesServices.findAllSequence();
    }

}
