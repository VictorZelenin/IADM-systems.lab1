package ua.kpi.cad.iadms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpertCompetence {
    private long id;
    private Expert expert;
    private double competence;
}
