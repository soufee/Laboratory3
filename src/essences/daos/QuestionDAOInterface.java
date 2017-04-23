package essences.daos;

import essences.models.Question;
import essences.models.Questions;

import java.util.ArrayList;

/**
 * Created by Shoma on 21.04.2017.
 */
public interface QuestionDAOInterface {
     Questions selectQuestions();
    void deleteQuestion(int id);
    void updateQuestion(Question q,int id);
    void insertQuestion(Question q);
}
