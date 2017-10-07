package ua.kpi.cad.iadms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpertAnswer {
    private long id;
    private Question question;
    private List<Variant> chosenVariants;
}
