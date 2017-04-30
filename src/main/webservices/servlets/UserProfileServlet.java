package main.webservices.servlets;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Shoma on 28.04.2017.
 */
@Controller
@RequestMapping(value = "user/checkoutpage")
public class UserProfileServlet {
    @RequestMapping(method = RequestMethod.GET)
    public String sayHello(Model model, String login, HttpServletResponse response) {

        model.addAttribute("login",login);
        Cookie cookie = new Cookie("login", login);
        response.addCookie(cookie);

        return "/user/checkoutpage";
    }
}
