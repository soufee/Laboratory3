package essences.daos;

import essences.models.Gamer;
import essences.models.Gamers;
import listeners.MySessionListener;
import main.utils.ConnectionFactory;
import org.apache.log4j.Logger;
import org.postgresql.util.PSQLException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Shoma on 21.04.2017.
 */
public class GamersDAO implements GamerDAOInterface{
    private static Logger userLogger = Logger.getLogger(MySessionListener.class);

    @Override
    public Gamers selectGamers() {
        Connection connection = ConnectionFactory.getConnection();
        Gamer q1 = null;
        Gamers q = new Gamers();
        try {
            userLogger.debug("Trying to make connection");
            Statement statement = connection.createStatement();
            ResultSet result =
                    statement.executeQuery("select * from public.gamer");
            while (result.next()) {

                q1 = new Gamer();
                q1.setRdr_id(result.getInt("rdr_id"));
                q1.setNiackname(result.getString("nickname"));
                q1.setScore(result.getInt("csore"));
                q1.setPassword(result.getString("password"));
                q1.setEmail(result.getString("email"));
                q1.setBlocked(result.getBoolean("isblocked"));
                q1.setAdmin(result.getBoolean("isadmin"));
                q.getGamers().add(q1);
                userLogger.debug("Gamer created " + q1);
           }


            connection.close();

        } catch (PSQLException e)
        {
        userLogger.debug("PSQL Mistake in GamersDAO "+e.getMessage());
        }
        catch (SQLException e) {
userLogger.debug("Ошибка с SQL "+e.getMessage());
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
    public void deleteGamer(int id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            userLogger.debug("Connection in deleteGamer DBParser");
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM public.gamer WHERE rdr_id = " + id);
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
    public void updateGamer(Gamer g,int id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            userLogger.debug("Connection in updateGamer DBParser");
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE gamer SET nickname = '" + g.getNiackname() + "', csore = " +
     g.getScore()+ ", password = '" + g.getPassword() + "', email = '"+g.getEmail()+"', isadmin = "+g.isAdmin()+", isblocked = " +
                            g.isBlocked() + " WHERE rdr_id = " + id);
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
    public void insertGamer(Gamer g) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            userLogger.debug("Connection in insertGamer DBParser");
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO gamer  (nickname, csore, password, email) VALUES (?,?, ?, ?)");
            preparedStatement.setString(1, g.getNiackname());
            preparedStatement.setInt(2, g.getScore());
            preparedStatement.setString(3, g.getPassword());
            preparedStatement.setString(4, g.getEmail());
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
    public void blockGamer(int id) {
        Connection connection = ConnectionFactory.getConnection();
        Gamer q1 = null;

        try {
            userLogger.debug("Trying to make connection");
            Statement statement = connection.createStatement();
            ResultSet result =
                    statement.executeQuery("select * from public.gamer where rdr_id = "+id);
           result.next();
            q1 = new Gamer();
            q1.setNiackname(result.getString("nickname"));
            q1.setScore(result.getInt("csore"));
            q1.setPassword(result.getString("password"));
            q1.setEmail(result.getString("email"));
            q1.setAdmin(result.getBoolean("isadmin"));
            q1.setBlocked(true);
      updateGamer(q1, id);

        }catch (SQLException e) {
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
    public void unblockGamer(int id) {
        Connection connection = ConnectionFactory.getConnection();
        Gamer q1 = null;

        try {
            userLogger.debug("Trying to make connection");
            Statement statement = connection.createStatement();
            ResultSet result =
                    statement.executeQuery("select * from public.gamer where rdr_id = "+id);
            result.next();
            q1 = new Gamer();
            q1.setNiackname(result.getString("nickname"));
            q1.setScore(result.getInt("csore"));
            q1.setPassword(result.getString("password"));
            q1.setEmail(result.getString("email"));
            q1.setAdmin(result.getBoolean("isadmin"));
            q1.setBlocked(false);
            updateGamer(q1, id);

        }catch (SQLException e) {
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
    public void makeAdmin(int id) {
        Connection connection = ConnectionFactory.getConnection();
        Gamer q1 = null;

        try {
            userLogger.debug("Trying to make connection");
            Statement statement = connection.createStatement();
            ResultSet result =
                    statement.executeQuery("select * from public.gamer where rdr_id = "+id);
            result.next();
            q1 = new Gamer();
            q1.setNiackname(result.getString("nickname"));
            q1.setScore(result.getInt("csore"));
            q1.setPassword(result.getString("password"));
            q1.setEmail(result.getString("email"));
            q1.setAdmin(true);
            q1.setBlocked(result.getBoolean("isblocked"));
            updateGamer(q1, id);

        }catch (SQLException e) {
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
    public void unadmin(int id) {
        Connection connection = ConnectionFactory.getConnection();
        Gamer q1 = null;

        try {
            userLogger.debug("Trying to make connection");
            Statement statement = connection.createStatement();
            ResultSet result =
                    statement.executeQuery("select * from public.gamer where rdr_id = "+id);
            result.next();
            q1 = new Gamer();
            q1.setNiackname(result.getString("nickname"));
            q1.setScore(result.getInt("csore"));
            q1.setPassword(result.getString("password"));
            q1.setEmail(result.getString("email"));
            q1.setAdmin(false);
            q1.setBlocked(result.getBoolean("isblocked"));
            updateGamer(q1, id);

        }catch (SQLException e) {
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
