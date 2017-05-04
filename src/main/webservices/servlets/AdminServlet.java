package main.webservices.servlets;

import main.DB.models.Gamer;
import main.DB.models.Gamers;
import main.DB.models.Question;
import main.DB.models.Questions;
import main.DB.service.GamerService;
import main.DB.service.GamerServiceInterface;
import main.DB.service.QuestionService;
import main.DB.service.QuestionServiceInterface;
import main.webservices.listeners.MySessionListener;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
   @Autowired
   GamerServiceInterface service;
   @Autowired
   QuestionServiceInterface service1;
    private static Logger userLogger = Logger.getLogger(MySessionListener.class);

    //TODO ControllerAdvice

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


    }


    private  ArrayList<Gamer> getGamers()
    {
//        service = new GamerService();
        Gamers listGamers = service.selectGamers();
        ArrayList<Gamer> list = (ArrayList<Gamer>) listGamers.getGamers();
        return list;

    }

    private ArrayList<Question> getQuestions()
    {
        //QuestionService service1 = new QuestionService();
        Questions listQuestions = service1.selectQuestions();
        ArrayList<Question> questList = (ArrayList<Question>) listQuestions.getQuestions();
        return questList;
    }

}
