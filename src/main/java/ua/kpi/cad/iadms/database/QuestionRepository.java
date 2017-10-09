package ua.kpi.cad.iadms.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ua.kpi.cad.iadms.entity.Expert;
import ua.kpi.cad.iadms.entity.Question;

import java.util.List;

@RepositoryRestResource
public interface QuestionRepository extends CrudRepository<Question, Long> {
    List<Question> findAll();
}
