package ua.kpi.cad.iadms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Variant {
    private long id;

    private int index;
    private String description;
    private double value;

    public String toString() {
        return String.format("Variant - %s, value - %4.3f", description, value);
    }
}
