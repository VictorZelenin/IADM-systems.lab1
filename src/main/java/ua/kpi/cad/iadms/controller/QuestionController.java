package ua.kpi.cad.iadms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kpi.cad.iadms.database.QuestionRepository;
import ua.kpi.cad.iadms.entity.Question;

import java.util.List;

@RestController
public class QuestionController {

    private final QuestionRepository repository;

    @Autowired
    public QuestionController(QuestionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/questions")
    public List<Question> getAllQuestions() {
        List<Question> questions = repository.findAll();

        return questions;
    }
}
