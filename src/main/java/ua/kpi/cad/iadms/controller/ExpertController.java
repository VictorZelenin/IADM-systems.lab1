package ua.kpi.cad.iadms.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.kpi.cad.iadms.engine.ExpertCompetenceEngine;
import ua.kpi.cad.iadms.engine.ExpertCompetenceEngineImpl;
import ua.kpi.cad.iadms.entity.Expert;
import ua.kpi.cad.iadms.entity.ExpertCompetence;

import java.util.List;

@RestController
public class ExpertController {

    private int numberOfExperts;

    @PostMapping("/api/experts/competence")
    public List<ExpertCompetence> calculateExpertCompetence(@RequestBody String input) {
        List<Expert> experts = parseInput(input);
        ExpertCompetenceEngine engine = new ExpertCompetenceEngineImpl();

        return engine.countExpertCompetence(experts, numberOfExperts);
    }

    private List<Expert> parseInput(String json) {
        return null;
    }
}
