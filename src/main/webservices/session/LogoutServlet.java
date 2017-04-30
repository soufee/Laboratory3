package main.webservices.session;

import main.webservices.listeners.MySessionListener;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Shoma on 23.04.2017.
 */

@Controller
@RequestMapping("/logout")
public class LogoutServlet {
    private static Logger userLogger = Logger.getLogger(MySessionListener.class);
    @RequestMapping(method= RequestMethod.GET)
    public String logout(HttpSession session) {
      try{  session.invalidate();
        session.removeAttribute("login");
        userLogger.debug("Выход пользователя");}
        catch (IllegalStateException exception)
        {
            userLogger.error(exception.getMessage());
        }
        return "redirect:/";
    }
}

//
//public class LogoutServlet extends HttpServlet {
//
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//        Cookie[] cookies = request.getCookies();
//        if(cookies != null){
//            for(Cookie cookie : cookies){
//                if(cookie.getName().equals("JSESSIONID")){
//                    System.out.println("JSESSIONID="+cookie.getValue());
//                    break;
//                }
//            }
//        }
//        //invalidate the session if exists
//        HttpSession session = request.getSession(false);
//        System.out.println("User="+session.getAttribute("user"));
//        if(session != null){
//            session.invalidate();
//        }
//        response.sendRedirect("index1.jsp");
//    }

//}