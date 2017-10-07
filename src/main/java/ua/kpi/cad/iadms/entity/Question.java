package ua.kpi.cad.iadms.entity;

import lombok.Data;

import java.util.List;

@Data
public class Question {
    private long id;
    private String description;
    private List<Variant> variants;

    public Question(long id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Question - %s, Variants - " + variants, description);
    }
}
