package ua.kpi.cad.iadms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expert {
    private long id;
    private String name;
    private List<ExpertAnswer> answer;

    public Expert(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
