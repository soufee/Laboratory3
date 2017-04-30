package main.webservices.servlets;

import main.DB.models.Gamer;
import main.DB.models.ObjectFactory;
import main.DB.models.Question;
import main.DB.service.GamerService;
import main.DB.service.QuestionService;
import main.webservices.listeners.MySessionListener;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Shoma on 28.04.2017.
 */
@Component
@RequestMapping(value = "/edit")
public class EditServlet {
    private static Logger userLogger = Logger.getLogger(MySessionListener.class);

    @RequestMapping(method = RequestMethod.GET)
    public String sayHello() {
        userLogger.debug("LOGINNNN");
        return "helloadmin";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest req,
                              @RequestParam(value = "iId", required = true) String id,
                              @RequestParam(value = "table", required = true) String table) {
        ModelAndView mav = new ModelAndView();
        ObjectFactory factory = new ObjectFactory();
        if (table.equals("question")) {
            QuestionService service = null;
            try {
                service = new QuestionService();
                Question question = service.getQuestById(Integer.parseInt(id));
                Question questionupdate;
                String quest = req.getParameter("iQuestion");
                if (quest == null || quest.equals(""))
                    quest = question.getQuest();
                String answer = req.getParameter("iAnswer");
                if (answer == null || answer.equals(""))
                    answer = question.getAnswer();
                String score = req.getParameter("iScore");
                if (score == null || score.equals(""))
                    score = String.valueOf(question.getScore());
                String hint = req.getParameter("iHint");
                if (hint == null || hint.equals(""))
                    hint = question.getHint();
questionupdate = factory.createQuestion(quest,answer,hint,Integer.parseInt(score));
service.updateQuestion(questionupdate,Integer.parseInt(id));
            } catch (Exception e) {
                userLogger.error("Возникла какая-то ошибка во время редактирования вопроса "+e.getMessage());
            }
            mav.setViewName("redirect:admin/helloadmin");
        } else if (table.equals("user")) {
            GamerService service = null;

            try {
                service = new GamerService();
                Gamer gamer = service.findGamerById(Integer.parseInt(id));
                Gamer gamerupdate;
                String nickname = req.getParameter("iNickname");
                if (quest == null || quest.equals(""))
                    quest = question.getQuest();
                String password = req.getParameter("iAnswer");
                if (answer == null || answer.equals(""))
                    answer = question.getAnswer();
                String email = req.getParameter("iScore");
                if (score == null || score.equals(""))
                    score = String.valueOf(question.getScore());
                boolean isadmin = req.getParameter("iHint");
                if (hint == null || hint.equals(""))
                    hint = question.getHint();
                questionupdate = factory.createQuestion(quest,answer,hint,Integer.parseInt(score));
                service.updateQuestion(questionupdate,Integer.parseInt(id));
            } catch (Exception e) {
                userLogger.error("Возникла какая-то ошибка во время редактирования вопроса "+e.getMessage());
            }
            mav.setViewName("redirect:admin/helloadmin");
        }


        return mav;
    }


}
