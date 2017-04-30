package main.DB.daos;

import main.DB.models.Gamer;
import main.DB.models.Gamers;

/**
 * Created by Shoma on 21.04.2017.
 */
public interface GamerDAOInterface {
   Gamers selectGamers();
    void deleteGamer(int id);
    void updateGamer(Gamer g,int id);
    void insertGamer(Gamer g);
    void blockGamer (int id);
    void unblockGamer (int id);
    void makeAdmin(int id);
    void unadmin (int id);
 Gamer findGamerById(int id);
}
