package main.webservices.servlets;

import main.DB.models.Gamer;
import main.DB.models.Question;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by Shoma on 28.04.2017.
 */
@Controller
//@SessionAttributes(value = "login")
@RequestMapping(value = "user/hello")
public class GameServlet {
    @RequestMapping(method = RequestMethod.GET)
    public String sayHello(Model model, String login, HttpServletResponse response) {
        Cookie cookie = new Cookie("login", login);
        response.addCookie(cookie);

        model.addAttribute("login",login);

        return "/user/hello";
    }
}