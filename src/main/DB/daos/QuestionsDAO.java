package main.DB.daos;

import main.DB.models.ObjectFactory;
import main.DB.models.Question;
import main.DB.models.Questions;
import main.webservices.listeners.MySessionListener;
import main.utils.ConnectionFactory;
import org.apache.log4j.Logger;
import org.postgresql.util.PSQLException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.*;

/**
 * Created by Shoma on 21.04.2017.
 */
@Repository
@Scope("prototype")
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
                q1.setQ_id(result.getInt("q_id"));
                q.getQuestions().add(q1);
                userLogger.debug("Making a Questions object from SQL " + q1);
            }

        } catch (PSQLException e) {
            userLogger.debug("PSQL Mistake in QuestionDAO" + e.getMessage());
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

    @Override
    public Question getQuestById(int id) {
        Connection connection = ConnectionFactory.getConnection();
        userLogger.debug("DBParser makes connection in selectQuestion, DBParser" + connection);

        ObjectFactory objectFactory = new ObjectFactory();
        Question q1 = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            userLogger.debug("Statement " + statement);
            ResultSet result =
                    statement.executeQuery("select * from public.questions where q_id = " + id);
            userLogger.debug("SQL " + result);

result.next();
            q1 = objectFactory.createQuestion(result.getString("question"), result.getString("answer"),
                    result.getString("hint"), result.getInt("score"));
            q1.setQ_id(result.getInt("q_id"));

            userLogger.debug("Making a Questions object from SQL " + q1);
        } catch (SQLException e) {
            userLogger.error(e.getMessage());
        } finally {
            try {
                connection.close();
            statement.close();
            } catch (SQLException e) {
                userLogger.error(e.getMessage());
            }
        }
        return q1;
    }
}
