package essences.service;

import essences.models.Gamer;
import essences.models.Gamers;

/**
 * Created by Shoma on 21.04.2017.
 */
public interface GamerServiceInterface {
    Gamers selectGamers();
    void deleteGamer(int id);
    void updateGamer(Gamer g, int id);
    void insertGamer(Gamer g);
    void blockGamer (int id);
    void unblockGamer (int id);
    void makeAdmin(int id);
    void unadmin (int id);
}
