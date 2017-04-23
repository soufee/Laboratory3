package essences.service;

import essences.daos.GamerDAOInterface;
import essences.daos.GamersDAO;
import essences.models.Gamer;
import essences.models.Gamers;
import listeners.MySessionListener;
import org.apache.log4j.Logger;

/**
 * Created by Shoma on 21.04.2017.
 */
public class GamerService implements GamerServiceInterface{
    private static Logger userLogger = Logger.getLogger(MySessionListener.class);
    private static GamerDAOInterface dao = new GamersDAO();
    @Override
    public Gamers selectGamers() {
userLogger.debug("Selecting gamers");
        return dao.selectGamers();
    }

    @Override
    public void deleteGamer(int id) {
        userLogger.debug("Deleting a gamer");
dao.deleteGamer(id);
    }

    @Override
    public void updateGamer(Gamer g, int id) {
        userLogger.debug("Updating a gamer");
dao.updateGamer(g,id);
    }

    @Override
    public void insertGamer(Gamer g) {
        userLogger.debug("Inserting a gamer");
dao.insertGamer(g);
    }

    @Override
    public void blockGamer(int id) {
        userLogger.debug("Blocking a gamer");
dao.blockGamer(id);
    }

    @Override
    public void unblockGamer(int id) {
        userLogger.debug("Unblocking a gamer");
dao.unblockGamer(id);
    }

    @Override
    public void makeAdmin(int id) {
        userLogger.debug("Making a gamer admin");
dao.makeAdmin(id);
    }

    @Override
    public void unadmin(int id) {
        userLogger.debug("Unmaking a gamer admin");
dao.unadmin(id);
    }
}
