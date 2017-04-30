package main.webservices.servlets;

import main.DB.service.GamerService;
import main.DB.service.QuestionService;
import main.webservices.listeners.MySessionListener;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Shoma on 28.04.2017.
 */
@Controller
@RequestMapping(value = "/delete")
public class DeleteServlet {
//    int gotId;
//    String table;
    private static Logger userLogger = Logger.getLogger(MySessionListener.class);

    @RequestMapping(method = RequestMethod.GET)
    public String sayHello() {
        userLogger.debug("LOGINNNN");
        return "helloadmin";
    }


    @RequestMapping( method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value = "idDel", required = true) String id,
                              @RequestParam(value = "table", required = true) String table) {
        ModelAndView mav = new ModelAndView();
        if (table.equals("question")) {
            QuestionService service = new QuestionService();
            service.deleteQuestion(Integer.parseInt(id));
            userLogger.debug("deleted the question " + id);
            mav.setViewName("redirect:admin/helloadmin");
        } else if (table.equals("user")) {
            GamerService service = new GamerService();
            service.deleteGamer(Integer.parseInt(id));
            userLogger.debug("deleted the user " + id);
            mav.setViewName("redirect:admin/helloadmin");
        }


        return mav;
    }


}
