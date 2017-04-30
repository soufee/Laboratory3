package main.webservices.servlets;

import main.DB.models.Gamer;
import main.DB.models.Gamers;
import main.DB.models.Question;
import main.DB.models.Questions;
import main.DB.service.GamerService;
import main.DB.service.QuestionService;
import main.webservices.listeners.MySessionListener;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Shoma on 22.04.2017.
 */
@Controller
public class AdminServlet {

    private static Logger userLogger = Logger.getLogger(MySessionListener.class);

    @RequestMapping( value = "/admin/helloadmin", method = RequestMethod.GET)
     public String sayHello(Model model, String login, HttpServletResponse response) {
        userLogger.debug("Admin Servlet is working");
        model.addAttribute("login",login);
        Cookie cookie = new Cookie("login", login);
     response.addCookie(cookie);
     //   model.addAttribute(cookie);

        ArrayList<Gamer> gamers = getGamers();
        model.addAttribute("gamers",gamers);
        ArrayList<Question> questions = getQuestions();
        model.addAttribute("questions",questions);
//        model.setViewName("redirect:admin/helloadmin");
        return "/admin/helloadmin";

//        return "/admin/helloadmin";
    }
//
//    @RequestMapping(method = RequestMethod.GET)
//    public String showList(Model model) {
//        LOGGER.debug("Зашли в метод showList в классе ListController");
//        model.addAttribute("list", studentService.getAllStudents());
//        LOGGER.debug("Возвращаем list");
//        return "listStudents";
//    }

//    @RequestMapping(value = "/admin/helloadmin", method = RequestMethod.POST)
//    public String showList(Model model, String login) {
//
//    }

    private  ArrayList<Gamer> getGamers()
    {GamerService service = new GamerService();
        Gamers listGamers = service.selectGamers();
        ArrayList<Gamer> list = (ArrayList<Gamer>) listGamers.getGamers();
        return list;

    }

    private ArrayList<Question> getQuestions()
    { QuestionService service1 = new QuestionService();
        Questions listQuestions = service1.selectQuestions();
        ArrayList<Question> questList = (ArrayList<Question>) listQuestions.getQuestions();
        return questList;
    }
//
//
//
//
//    GamerService service = new GamerService();
//    QuestionService service1 = new QuestionService();
//
//
//
//
//
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String login = (String) (req).getSession().getAttribute("user");
//        req.setAttribute("user", login);
////    String login = (String) req.getAttribute("user");
//
//
//        req.setAttribute("value", "Список пользователей");
//        Gamers listGamers = service.selectGamers();
//        Questions listQuestions = service1.selectQuestions();
//        userLogger.debug("Передаем список пользователей на страницу "+listGamers);
//        ArrayList<Gamer> list = (ArrayList<Gamer>) listGamers.getGamers();
//        userLogger.debug("Array пользователей получен" +list);
//
//
//        ArrayList<Question> questList = (ArrayList<Question>) listQuestions.getQuestions();
//        userLogger.debug("Список вопросов получен" + questList);
//        req.setAttribute("list", list);
//        req.setAttribute("quests", questList);
//        userLogger.debug("quests и list отправлены как параметр");
////        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/helloadmin.jsp");
////        dispatcher.forward(req, resp);
//    }


}
