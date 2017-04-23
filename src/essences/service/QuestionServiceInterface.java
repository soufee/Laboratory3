package essences.service;

import essences.models.Question;
import essences.models.Questions;

/**
 * Created by Shoma on 21.04.2017.
 */
public interface QuestionServiceInterface {
    Questions selectQuestions();
    void deleteQuestion(int id);
    void updateQuestion(Question q, int id);
    void insertQuestion(Question q);
}
