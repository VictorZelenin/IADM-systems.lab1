package ua.kpi.cad.iadms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table(name = "experts")
public class Expert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @OneToMany
    @JoinColumn(name = "expert_id")
    private List<ExpertAnswer> answer;

    public Expert(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
