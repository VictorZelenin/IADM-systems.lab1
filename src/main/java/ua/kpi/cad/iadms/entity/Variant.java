package ua.kpi.cad.iadms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question_variant", schema = "public")
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "variant_index")
    private int index;
    @Column(name = "description")
    private String description;
    @Column(name = "value")
    private double value;

    public String toString() {
        return String.format("Variant - %s, value - %4.3f", description, value);
    }
}
