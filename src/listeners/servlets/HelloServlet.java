package listeners.servlets;

import general.Logging;
import listeners.MySessionListener;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Shoma on 21.04.2017.
 */
public class HelloServlet extends HttpServlet {
    private static Logger userLogger = Logger.getLogger(MySessionListener.class);
    public static String userName;
private static Logging userService = new Logging();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        userLogger.debug("loging: Получил логин и пароль");
        if (userService.auth(login, password) != null) {
            HttpSession session = req.getSession(true);

            session.setMaxInactiveInterval(5);
            userName = login;
        userLogger.debug("Авторизация ");
            req.getSession().setAttribute("userLogin", login);
           if (userService.auth(login, password).isAdmin())
            resp.sendRedirect(req.getContextPath() + "/helloadmin");
           else  resp.sendRedirect(req.getContextPath() + "/hello.jsp");

            userLogger.debug("Логимся " + this.getClass().getName());
        } else
            {
                resp.sendRedirect(req.getContextPath() + "/registration.jsp");
                userLogger.debug("Авторизация не удалась");
            }
    }
    }

