package main.webservices.servlets;

import main.DB.models.Gamer;
import main.DB.models.ObjectFactory;
import main.DB.service.GamerService;
import main.webservices.listeners.MySessionListener;
import main.utils.ConnectionFactory;
import main.utils.exceptions.PassIncorrectException;
import main.utils.exceptions.UserIsRegisteredException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Shoma on 22.04.2017.
 */

@Controller
@RequestMapping(value = "/registration")
public class RegistrationServlet{

    private static Logger userLogger = Logger.getLogger(MySessionListener.class);
//    String login;
//    String email;
//    String password;


    @RequestMapping(method = RequestMethod.GET)
    public String sayHello() {
        userLogger.debug("Работает сервлет регистрации");
        return "registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView registration(@RequestParam(value = "login", required = true) String login,
                                    @RequestParam(value = "password", required = true) String password,
                                    @RequestParam(value = "email", required = true) String email) {
        ModelAndView mav = new ModelAndView();
        Gamer gamer = null;
        if (!(isContainUser(login))&&(!(isContainEmail(email))))
        {
            ObjectFactory factory = new ObjectFactory();
            gamer = factory.createGamer(login,password,email,false);
            addUser(gamer);
            mav.setViewName("redirect:user/hello");
        } else
            {
                userLogger.error("Пользователь с таким логином или емайлом существует");
                mav.addObject("Пользователь с таким логином или емайлом существует");
                mav.setViewName("redirect:registration");
            }


        return mav;
    }


//
//
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        userLogger.debug("doGet сервлета RegistrationServlet");
//        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
//    }
//
//
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        userLogger.debug("doPost сервлета RegistrationServlet");
//        login = req.getParameter("login");
//        password = req.getParameter("password");
//        String password2 = req.getParameter("passwordRepeat");
//        email = req.getParameter("email");
//        userLogger.debug("Получены параметры " + login + ", " + email);
//        if (password.equals(password2)) {
//            userLogger.debug("Пароли совпадают");
//            if (!isContainUser(login)) {
//                userLogger.debug("Логин и email свободны");
//                ObjectFactory factory = new ObjectFactory();
//                Gamer gamer = factory.createGamer(login, password2, email, false);
//                addUser(gamer);
//                HttpSession session = req.getSession(true);
//                session.setAttribute("userLogin", login);
//                PrintWriter out = resp.getWriter();
//                out.write("Hi " + login);
//                resp.sendRedirect(req.getContextPath() + "/user/hello.jsp");
//            } else {
//                try {
//                    PrintWriter out = resp.getWriter();
//                    out.write("Пользователь с таким логином или email уже зарегистрирован в нашей системе");
//                    throw new UserIsRegisteredException();
//
//                } catch (UserIsRegisteredException e) {
//                    PrintWriter out = resp.getWriter();
//                    out.write("Пользователь с таким логином или email уже зарегистрирован в нашей системе");
//
//                    userLogger.debug(e.getMessage());
//                }
//            }
//
//        } else {
//            try {
//                PrintWriter out = resp.getWriter();
//                out.write("Пароли должны совпадать");
//                throw new PassIncorrectException();
//            } catch (PassIncorrectException e) {
//                PrintWriter out = resp.getWriter();
//                out.write("Пароли должны совпадать");
//                userLogger.debug(e.getMessage());
//            }
//        }
//    }

    private boolean isContainUser(String login) {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;
        Statement statement1 = null;

        try {
            statement1 = connection.createStatement();
            ResultSet result1 = statement1.executeQuery("select * from public.gamer WHERE nickname = '" + login + "'");
            if (!result1.next()) {
                return false;
            } else
                {
            return true;
                }
            } catch (SQLException e1) {
            userLogger.error(e1.getMessage());
        }
return false;
    }


    private boolean isContainEmail(String email) {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;
        Statement statement1 = null;

        try {
            statement1 = connection.createStatement();
            ResultSet result1 = statement1.executeQuery("select * from public.gamer WHERE email = '" + email + "'");
            if (!result1.next()) {
                return false;
            } else
            {
                return true;
            }
        } catch (SQLException e1) {
            userLogger.error(e1.getMessage());
        }
        return false;
    }


    private void addUser(Gamer g) {

        GamerService service = new GamerService();
        service.insertGamer(g);

    }
}
