package listeners.session;

import essences.models.Gamer;
import general.Logging;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Shoma on 23.04.2017.
 */
public class LoginServlet extends HttpServlet {
    private String userID = "";
    private String password = "";
    private static Logging userService = new Logging();
    public static String user;
   public static Gamer gamer;
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


     user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        if (userService.auth(user, pwd) != null) {
         gamer = userService.auth(user,pwd);
            userID = gamer.getNiackname();
            password = gamer.getPassword();


        }
        if (userID.equals(user) && password.equals(pwd)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            session.setMaxInactiveInterval(30 * 60);
            Cookie userName = new Cookie("user", user);
            userName.setMaxAge(30 * 60);
            response.addCookie(userName);
            if (userService.auth(user, password).isAdmin())
                response.sendRedirect(request.getContextPath() + "/admin/helloadmin");
            else response.sendRedirect(request.getContextPath() + "/user/hello.jsp");

        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher( "/index.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request, response);
        }

    }

}

