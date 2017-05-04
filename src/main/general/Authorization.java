package main.general;

import main.DB.models.Gamer;
import main.DB.models.ObjectFactory;
import main.utils.exceptions.PassIncorrectException;
import main.webservices.listeners.MySessionListener;
import main.utils.ConnectionFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Shoma on 22.04.2017.
 */
public class Authorization {
    private static Logger userLogger = Logger.getLogger(MySessionListener.class);
    public Gamer findUserByLoginAndPassword(String login, String password) throws PassIncorrectException {
        Gamer user = null;
        PreparedStatement statement;
        try (Connection connection = ConnectionFactory.getConnection()) {
            statement = connection.prepareStatement("SELECT * FROM public.gamer WHERE nickname = ? AND password = ?");

            statement.setString(1, login);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createEntity(resultSet);
            } else
                {
                    System.out.println("Пароль и логин не совпали");
                    throw new PassIncorrectException();
                }

        } catch (SQLException e) {
            e.printStackTrace();
        } return user;
    }

    private Gamer createEntity(ResultSet resultSet) throws SQLException {
        ObjectFactory factory = new ObjectFactory();
        boolean adm = resultSet.getBoolean("isadmin");
        Gamer gamer = factory.createGamer(resultSet.getString("nickname"),resultSet.getString("password"),resultSet.getString("email"), adm);

userLogger.debug("Created an user "+gamer);
        return gamer;

    }

public Gamer auth(String login, String password) throws PassIncorrectException {

        Gamer user = findUserByLoginAndPassword(login, password);
    if (user != null && user.isBlocked()) {
        return null;
        }
        return user;
    }

    public boolean isadmin (Gamer g) throws PassIncorrectException {

     String login = g.getNiackname();
     String password = g.getPassword();
     g = findUserByLoginAndPassword(login,password);
        if (g != null && !(g.isBlocked())) {
          if (g.isAdmin()) return true;
          else return false;
        }
        return false;
    }
}
