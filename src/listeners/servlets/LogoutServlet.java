package listeners.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Shoma on 22.04.2017.
 */
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //    resp.setContentType("text/html;charset=UTF-8");
        String message = "Вы успешно вышли.";

        HttpSession session = req.getSession(false);

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                cookies[i].setValue(null);
                cookies[i].setMaxAge(0);
                resp.addCookie(cookie);
            }
        }

        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("Cache-Control","no-store");
        resp.setDateHeader("Expires", 0);
        resp.setHeader("Pragma","no-cache");
        session.removeAttribute("name");
        session.invalidate();
        req.getSession(true);

        RequestDispatcher rs = req.getRequestDispatcher("/index.jsp");
        req.setAttribute("message", message);
        rs.forward(req, resp);

    }
}
