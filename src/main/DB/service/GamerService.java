package main.DB.service;

import main.DB.daos.GamerDAOInterface;
import main.DB.models.Gamer;
import main.DB.models.Gamers;
import main.webservices.listeners.MySessionListener;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by Shoma on 21.04.2017.
 */
@Component
@Scope("prototype")
public class GamerService implements GamerServiceInterface {
    private static Logger userLogger = Logger.getLogger(MySessionListener.class);


    private GamerDAOInterface gamerDAOInterface;

    public void setGamerDAOInterface(GamerDAOInterface gamerDAOInterface) {
        this.gamerDAOInterface = gamerDAOInterface;
    }

    @Override
    public Gamers selectGamers() {
        userLogger.debug("Selecting gamers");
        return gamerDAOInterface.selectGamers();
    }

    @Override
    public void deleteGamer(int id) {
        userLogger.debug("Deleting a gamer");
        gamerDAOInterface.deleteGamer(id);
    }

    @Override
    public void updateGamer(Gamer g, int id) {
        userLogger.debug("Updating a gamer");
        gamerDAOInterface.updateGamer(g, id);
    }

    @Override
    public void insertGamer(Gamer g) {
        userLogger.debug("Inserting a gamer");
        gamerDAOInterface.insertGamer(g);
    }

    @Override
    public void blockGamer(int id) {
        userLogger.debug("Blocking a gamer");
        gamerDAOInterface.blockGamer(id);
    }

    @Override
    public void unblockGamer(int id) {
        userLogger.debug("Unblocking a gamer");
        gamerDAOInterface.unblockGamer(id);
    }

    @Override
    public void makeAdmin(int id) {
        userLogger.debug("Making a gamer admin");
        gamerDAOInterface.makeAdmin(id);
    }

    @Override
    public void unadmin(int id) {
        userLogger.debug("Unmaking a gamer admin");
        gamerDAOInterface.unadmin(id);
    }

    @Override
    public Gamer findGamerById(int id) {
        return gamerDAOInterface.findGamerById(id);
    }


}
