package ua.kpi.cad.iadms.engine;

import ua.kpi.cad.iadms.entity.Expert;
import ua.kpi.cad.iadms.entity.ExpertCompetence;

import java.util.List;

public interface ExpertCompetenceEngine {

    List<ExpertCompetence> countExpertCompetence(List<Expert> experts, int quantityOfQuestions);
}
