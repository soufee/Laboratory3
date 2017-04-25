package listeners.servlets;

import essences.models.Gamer;
import essences.models.Gamers;
import essences.models.Question;
import essences.models.Questions;
import essences.service.GamerService;
import essences.service.QuestionService;
import listeners.MySessionListener;
import listeners.session.LoginServlet;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Shoma on 22.04.2017.
 */
public class AdminServlet extends HttpServlet {
    private static Logger userLogger = Logger.getLogger(MySessionListener.class);
    GamerService service = new GamerService();
    QuestionService service1 = new QuestionService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = (String) (req).getSession().getAttribute("user");
        req.setAttribute("user", login);
//    String login = (String) req.getAttribute("user");


        req.setAttribute("value", "Список пользователей");
        Gamers listGamers = service.selectGamers();
        Questions listQuestions = service1.selectQuestions();
        userLogger.debug("Передаем список пользователей на страницу "+listGamers);
        ArrayList<Gamer> list = (ArrayList<Gamer>) listGamers.getGamers();
        userLogger.debug("Array пользователей получен" +list);


        ArrayList<Question> questList = (ArrayList<Question>) listQuestions.getQuestions();
        userLogger.debug("Список вопросов получен" + questList);
        req.setAttribute("list", list);
        req.setAttribute("quests", questList);
        userLogger.debug("quests и list отправлены как параметр");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/helloadmin.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
