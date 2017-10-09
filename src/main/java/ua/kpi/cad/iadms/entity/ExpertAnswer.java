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
//@Table(name = "expert_answer")
public class ExpertAnswer {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
    private long id;

//    @OneToMany
//    @JoinColumn(name = "question_id")
    private Question question;

    private List<Variant> chosenVariants;
}
