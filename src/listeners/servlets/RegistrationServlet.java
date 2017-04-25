package listeners.servlets;

import essences.models.Gamer;
import essences.models.ObjectFactory;
import essences.service.GamerService;
import listeners.MySessionListener;
import main.utils.ConnectionFactory;
import main.utils.eceptions.PassIncorrectException;
import main.utils.eceptions.UserIsRegisteredException;
import org.apache.log4j.Logger;

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
public class RegistrationServlet extends HttpServlet {
    private static Logger userLogger = Logger.getLogger(MySessionListener.class);
    String login;
    String email;
    String password;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userLogger.debug("doGet сервлета RegistrationServlet");
        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userLogger.debug("doPost сервлета RegistrationServlet");
        login = req.getParameter("login");
        password = req.getParameter("password");
        String password2 = req.getParameter("passwordRepeat");
        email = req.getParameter("email");
        userLogger.debug("Получены параметры " + login + ", " + email);
        if (password.equals(password2)) {
            userLogger.debug("Пароли совпадают");
            if (!isContainUser(login, email)) {
                userLogger.debug("Логин и пароль свободны");
                ObjectFactory factory = new ObjectFactory();
                Gamer gamer = factory.createGamer(login, password2, email);
                addUser(gamer);
                HttpSession session = req.getSession(true);
                session.setAttribute("userLogin", login);
                PrintWriter out = resp.getWriter();
                out.write("Hi " + login);
                resp.sendRedirect(req.getContextPath() + "/user/hello.jsp");
            } else {
                try {
                    PrintWriter out = resp.getWriter();
                    out.write("Пользователь с таким логином или email уже зарегистрирован в нашей системе");
                    throw new UserIsRegisteredException();

                } catch (UserIsRegisteredException e) {
                    PrintWriter out = resp.getWriter();
                    out.write("Пользователь с таким логином или email уже зарегистрирован в нашей системе");

                    userLogger.debug(e.getMessage());
                }
            }

        } else {
            try {
                PrintWriter out = resp.getWriter();
                out.write("Пароли должны совпадать");
                throw new PassIncorrectException();
            } catch (PassIncorrectException e) {
                PrintWriter out = resp.getWriter();
                out.write("Пароли должны совпадать");
                userLogger.debug(e.getMessage());
            }
        }
    }

    private boolean isContainUser(String login, String email) {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;
        Statement statement1 = null;

        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from public.gamer WHERE email = '" + email + "'");
            statement1 = connection.createStatement();
            ResultSet result1 = statement1.executeQuery("select * from public.gamer WHERE nickname = '" + login + "'");
            if (!result.next() && !result1.next()) {
                return false;
            } else
            if ((email.equals(result.getString("email"))) || (login.equals(result1.getString("nickname")))) {
                return true;
            }
            return false;
        }catch (Exception e)
        {
            userLogger.debug("Ошибка проверки наличия в базе такого пользователя "+e.getMessage());
        }
return false;
    }

    private void addUser(Gamer g) {

        GamerService service = new GamerService();
        service.insertGamer(g);

    }
}
