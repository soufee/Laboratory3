package main.DB.service;

import main.DB.models.Question;
import main.DB.models.Questions;

/**
 * Created by Shoma on 21.04.2017.
 */
public interface QuestionServiceInterface {
    Questions selectQuestions();
    void deleteQuestion(int id);
    void updateQuestion(Question q, int id);
    void insertQuestion(Question q);
    Question getQuestById(int id);
}
