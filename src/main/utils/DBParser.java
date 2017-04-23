package main.utils;

import essences.models.Gamer;
import essences.models.Gamers;
import essences.models.Question;
import essences.models.Questions;
import listeners.MySessionListener;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.*;

/**
 * Created by Shoma on 15.04.2017.
 */
public class DBParser {
    static final Logger userLogger = LogManager.getLogger(MySessionListener.class);


    public void selectQuestions() {
        Connection connection = ConnectionFactory.getConnection();
        userLogger.debug("DBParser makes connection in selectQuestion, DBParser" + connection);
        try {
            Statement statement = connection.createStatement();
            userLogger.debug("Statement " + statement);
            ResultSet result =
                    statement.executeQuery("select * from public.questions");
            userLogger.debug("SQL " + result);
            JAXBContext context;
            Marshaller marshaller = null;
            Question q1 = null;
            Questions q = new Questions();

            while (result.next()) {

                q1 = new Question();
                q1.setQuest(result.getString(2));
                q1.setAnswer(result.getString(3));
                q1.setScore(result.getInt("score"));
                q1.setHint(result.getString("hint"));
                q.getQuestions().add(q1);
                userLogger.debug("Making a Questions object from SQL " + q1);
                context = JAXBContext.newInstance(Questions.class);

                marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                userLogger.debug("Marshaller " + marshaller);
            }
            File file = new File("question.xml");
            userLogger.debug("making file question.xml " + file);
            marshaller.marshal(q, file);
            statement.close();

        } catch (SQLException e) {
            userLogger.debug("Mistake with SQL " + e.getMessage());
        } catch (JAXBException e) {
            userLogger.debug("Mistake with JAXB " + e.getMessage());
        } finally {

            try {
                connection.close();
            } catch (SQLException e) {
                userLogger.debug("Mistake with SQL " + e.getMessage());
            }
        }


    }


    public void selectGamers() {
        Connection connection = ConnectionFactory.getConnection();
        try {
            userLogger.debug("Trying to make connection");
            Statement statement = connection.createStatement();
            ResultSet result =
                    statement.executeQuery("select * from public.gamer");
            JAXBContext context;
            Marshaller marshaller = null;
            Gamer q1 = null;
            Gamers q = new Gamers();
            while (result.next()) {

                q1 = new Gamer();
                q1.setNiackname(result.getString("nickname"));
                q1.setScore(result.getInt("csore"));
                q1.setPassword(result.getString("password"));
                q.getGamers().add(q1);
                userLogger.debug("Gamer created " + q1);
                context = JAXBContext.newInstance(Gamers.class);

                marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            }
            File file = new File("gamer.xml");
            userLogger.debug("File created " + file);
            marshaller.marshal(q, file);
            userLogger.debug("Marshaller wrote a file " + file);
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        } finally {

            try {
                connection.close();
            } catch (SQLException e) {
                userLogger.debug("Mistake with SQL " + e.getMessage());
            }
        }
    }


    public void insertQuestion(String quest, String answer, int score, String hint) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            userLogger.debug("Connection in insertQuestion DBParser");
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO questions  (question, answer, score, hint) VALUES (?,?, ?,?)");
            preparedStatement.setString(1, quest);
            preparedStatement.setString(2, answer);
            preparedStatement.setInt(3, score);
            preparedStatement.setString(4, hint);
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

    public void updateQuestion(String fieldForSet, String whatToSet, String where) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            userLogger.debug("Connection in updateQuestion DBParser");
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE questions SET " + fieldForSet + "= '" + whatToSet + "'" + " WHERE " + where);
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

    public void deleteQuestion(String where) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            userLogger.debug("Connection in deleteQuestion DBParser");
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM questions WHERE " + where);
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

    public void insertGamer(String nickname, String password, int score, String email) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            userLogger.debug("Connection in insertGamer DBParser");
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO gamer  (nickname, csore, password, email) VALUES (?,?, ?, ?)");
            preparedStatement.setString(1, nickname);
            preparedStatement.setInt(2, score);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, email);
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

    public void updateGamer(String fieldForSet, String whatToSet, String where) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            userLogger.debug("Connection in updateGamer DBParser");
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE gamer SET " + fieldForSet + "= '" + whatToSet + "'" + " WHERE " + where);
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

    public void deleteGamer(String where) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            userLogger.debug("Connection in deleteGamer DBParser");
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM gamer WHERE " + where);
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

    public void cleanGamers() {
        Connection connection = ConnectionFactory.getConnection();
        try {
            userLogger.debug("Connection in cleanGamers DBParser");
            PreparedStatement preparedStatement =
                    connection.prepareStatement("truncate table gamer");
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

    public void cleanQuestions() {
        Connection connection = ConnectionFactory.getConnection();
        try {
            userLogger.debug("Connection in cleanQuestion DBParser");
            PreparedStatement preparedStatement =
                    connection.prepareStatement("truncate table questions");
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

    public void fromXMLtoDBQuest() {
        try {
            userLogger.debug("fromXMLtoDBQuest");
            File file = new File("question.xml");
            Unmarshaller unmarshaller = JAXBContext.newInstance(Questions.class).createUnmarshaller();
            Questions questions;
            questions = (Questions) unmarshaller.unmarshal(file);
            for (int i = 0; i < questions.getQuestions().size(); i++) {
                Question q = questions.getQuestions().get(i);
                insertQuestion(q.getQuest(), q.getAnswer(), q.getScore(), q.getHint());
            }
        } catch (JAXBException e) {
            userLogger.debug("Mistake with JAXB " + e.getMessage());
        }
    }

    public void fromXMLtoDBGamer() {
        try {
            userLogger.debug("fromXMLtoDBGamer");
            File file = new File("gamer.xml");
            Unmarshaller unmarshaller = JAXBContext.newInstance(Gamers.class).createUnmarshaller();
            Gamers gamers;
            gamers = (Gamers) unmarshaller.unmarshal(file);
            for (int i = 0; i < gamers.getGamers().size(); i++) {
                Gamer q = gamers.getGamers().get(i);
                insertGamer(q.getNiackname(), q.getPassword(), q.getScore(), q.getEmail());
            }
        } catch (JAXBException e) {
            userLogger.debug("Mistake with JAXB " + e.getMessage());
        }
    }

}





