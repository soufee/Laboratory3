package essences.daos;

import essences.models.ObjectFactory;
import essences.models.Question;
import essences.models.Questions;
import listeners.MySessionListener;
import main.utils.ConnectionFactory;
import org.apache.log4j.Logger;
import org.postgresql.util.PSQLException;

import java.sql.*;

/**
 * Created by Shoma on 21.04.2017.
 */
public class QuestionsDAO implements QuestionDAOInterface {
    private static Logger userLogger = Logger.getLogger(MySessionListener.class);

    @Override
    public Questions selectQuestions() {
        Connection connection = ConnectionFactory.getConnection();
        userLogger.debug("DBParser makes connection in selectQuestion, DBParser" + connection);
        Questions q = null;
        ObjectFactory objectFactory = new ObjectFactory();
        Question q1 = null;
        try {
            Statement statement = connection.createStatement();
            userLogger.debug("Statement " + statement);
            ResultSet result =
                    statement.executeQuery("select * from public.questions");
            userLogger.debug("SQL " + result);
            q = new Questions();
            while (result.next()) {
                q1 = objectFactory.createQuestion(result.getString(2), result.getString("answer"),
                        result.getString("hint"), result.getInt("score"));
                q.getQuestions().add(q1);
                userLogger.debug("Making a Questions object from SQL " + q1);
            }
            statement.close();
        } catch (SQLException e) {
            userLogger.debug("Mistake with SQL " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                userLogger.debug("Mistake with SQL " + e.getMessage());
            }
        }
        return q;
    }

    @Override
    public void deleteQuestion(int id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            userLogger.debug("Connection in deleteQuestion DBParser");
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM public.questions WHERE q_id = " + id);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            userLogger.debug("Mistake with SQL " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                userLogger.debug("Mistake with SQL " + e.getMessage());
            }
        }
    }

    @Override
    public void updateQuestion(Question q, int id) {
        Connection connection = ConnectionFactory.getConnection();
        userLogger.debug("Установлено соединение в методе updateById класса " + this.getClass().getName());
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("UPDATE public.questions SET question = '"
                    + q.getQuest() + "', answer = '" + q.getAnswer() + "', score = " + q.getScore() + ", hint = '" + q.getHint() + "' WHERE q_id = " + id);
            userLogger.debug("Вызван SQL запрос UPDATE");
            statement.executeQuery();

        } catch (PSQLException e) {
            userLogger.debug("Ошибка PSQL " + e.getMessage());

        } catch (SQLException e) {
            userLogger.debug("Ошибка SQL " + e.getMessage());

        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                userLogger.debug("Mistake with SQL " + e.getMessage());
            }
        }
    }

    @Override
    public void insertQuestion(Question q) {
        Connection connection = ConnectionFactory.getConnection();


        try {
            userLogger.debug("Connection in insertQuestion DBParser");
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO public.questions  (question, answer, score, hint) VALUES (?,?, ?,?)");
            preparedStatement.setString(1, q.getQuest());
            preparedStatement.setString(2, q.getAnswer());
            preparedStatement.setInt(3, q.getScore());
            preparedStatement.setString(4, q.getHint());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            userLogger.debug("Mistake with SQL " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                userLogger.debug("Mistake with SQL " + e.getMessage());
            }
        }
    }
}
