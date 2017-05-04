package main.webservices.servlets;

import main.DB.models.Gamer;
import main.DB.models.ObjectFactory;
import main.DB.models.Question;
import main.DB.service.GamerService;
import main.DB.service.GamerServiceInterface;
import main.DB.service.QuestionService;
import main.DB.service.QuestionServiceInterface;
import main.webservices.listeners.MySessionListener;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.registry.infomodel.User;

/**
 * Created by Shoma on 28.04.2017.
 */
@Controller
public class AddServlet {
   @Autowired
   GamerServiceInterface service;
   @Autowired
    QuestionServiceInterface service1;

    private static Logger userLogger = Logger.getLogger(MySessionListener.class);
    @RequestMapping(value = "/admin/adduser", method = RequestMethod.GET)
       public String adduser   (@RequestParam(value = "nickname", required = true) String nickname,
                                @RequestParam(value = "password", required = true) String password,
                                @RequestParam(value = "email", required = true) String email,
                                @RequestParam(value = "isadmin",required = false) String  isadmin)
    {
        userLogger.debug("работает AddServlet для создания Игрока");
        ObjectFactory factory = new ObjectFactory();
        boolean adm;
        if ((isadmin!=null)&&(isadmin.equalsIgnoreCase("On")))
            adm = true;
        else adm = false;

       Gamer gamer =  factory.createGamer(nickname,password,email, adm);


        userLogger.debug("Создан Gamer "+gamer);
      //  GamerService service = new GamerService();
        service.insertGamer(gamer);
        userLogger.debug("Вызван сервис добавления игрока в базу данных");
        return "admin/adduser";
    }

    @RequestMapping(value = "/admin/addquestion", method = RequestMethod.POST)
    public String addquestion (@RequestParam(value = "question", required = true) String question,
                               @RequestParam(value = "answer", required = true) String answer,
                               @RequestParam(value = "hint", required = true) String hint,
                               @RequestParam(value = "score", required = true) int score)
    {
        userLogger.debug("работает AddServlet для создания Вопроса");
        ObjectFactory factory = new ObjectFactory();
        Question question1 =  factory.createQuestion(question,answer,hint,score);
        userLogger.debug("Создан вопрос "+question1);
     //   QuestionService service1 = new QuestionService();
        service1.insertQuestion(question1);
        userLogger.debug("Вызывается сервис для добавления вопроса в базу");
        return "admin/addquestion";
    }


}
