package listeners.servlets;

import essences.models.Gamer;
import essences.models.Gamers;
import essences.service.GamerService;
import listeners.MySessionListener;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shoma on 22.04.2017.
 */
public class AdminServlet extends HttpServlet {
    private static Logger userLogger = Logger.getLogger(MySessionListener.class);
    GamerService service = new GamerService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("value", "Список пользователей");
        Gamers listGamers = service.selectGamers();
        userLogger.debug("Передаем список пользователей на страницу "+listGamers);
        ArrayList<Gamer> list = (ArrayList<Gamer>) listGamers.getGamers();
        userLogger.debug("Array пользователей получен" +list);

        req.setAttribute("list", list);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/helloadmin.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
