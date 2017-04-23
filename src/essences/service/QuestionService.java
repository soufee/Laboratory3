package essences.service;

import essences.daos.QuestionDAOInterface;
import essences.daos.QuestionsDAO;
import essences.models.Question;
import essences.models.Questions;
import listeners.MySessionListener;
import org.apache.log4j.Logger;

/**
 * Created by Shoma on 21.04.2017.
 */
public class QuestionService implements QuestionServiceInterface {
    private static Logger userLogger = Logger.getLogger(MySessionListener.class);
    private static QuestionDAOInterface dao = new QuestionsDAO();

    @Override
    public Questions selectQuestions() {
        userLogger.debug("Selecting questions");
        return dao.selectQuestions();
    }

    @Override
    public void deleteQuestion(int id) {
        userLogger.debug("Deleting a question");
        dao.deleteQuestion(id);
    }

    @Override
    public void updateQuestion(Question q, int id) {
        userLogger.debug("Updating a question");
        dao.updateQuestion(q, id);
    }

    @Override
    public void insertQuestion(Question q) {
        userLogger.debug("Inserting a question");
        dao.insertQuestion(q);
    }
}
