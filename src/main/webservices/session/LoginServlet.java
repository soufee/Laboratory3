package main.webservices.session;

import main.DB.models.Gamer;
import main.general.Authorization;
import main.utils.exceptions.PassIncorrectException;
import main.webservices.listeners.MySessionListener;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Shoma on 23.04.2017.
 */
@Controller
@RequestMapping(value = "/")
public class LoginServlet {
    private static Authorization userService = new Authorization();
    public static Gamer gamer = null;
    private static Logger userLogger = Logger.getLogger(MySessionListener.class);


    @RequestMapping(method = RequestMethod.GET)
    public String sayHello() {
        userLogger.debug("LOGINNNN");
        return "index1";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value = "user", required = true) String login,
                              @RequestParam(value = "pwd", required = true) String password) throws PassIncorrectException {
        ModelAndView mav = new ModelAndView();

        if ((gamer = userService.auth(login, password)) != null) {

            userLogger.debug("USER!=NULL");
            //req.getSession().setAttribute("login", login);
            mav.addObject("login", login);
            if (gamer.isAdmin())
                mav.setViewName("redirect:admin/helloadmin");
            else
                mav.setViewName("redirect:user/hello");
            userLogger.debug("login: " + login);
            //resp.sendRedirect(req.getContextPath() + "/listStudents");
        } else {
            mav.setViewName("redirect:/");
            userLogger.debug("USER==NULL");
        }

        return mav;
    }
}
















//
//
//    protected void doPost(HttpServletRequest request,
//                          HttpServletResponse response) throws ServletException, IOException {
//
//
//     user = request.getParameter("user");
//        String pwd = request.getParameter("pwd");
//
//        if (userService.auth(user, pwd) != null) {
//         gamer = userService.auth(user,pwd);
//            userID = gamer.getNiackname();
//            password = gamer.getPassword();
//
//
//        }
//        if (userID.equals(user) && password.equals(pwd)) {
//            HttpSession session = request.getSession();
//            session.setAttribute("user", user);
//
//            session.setMaxInactiveInterval(30 * 60);
//            Cookie userName = new Cookie("user", user);
//            userName.setMaxAge(30 * 60);
//            response.addCookie(userName);
//            if (userService.auth(user, password).isAdmin())
//                response.sendRedirect(request.getContextPath() + "/admin/helloadmin");
//            else response.sendRedirect(request.getContextPath() + "/user/hello.jsp");
//
//        } else {
//            RequestDispatcher rd = getServletContext().getRequestDispatcher( "/index1.jsp");
//            PrintWriter out = response.getWriter();
//            out.println("<font color=red>Either user name or password is wrong.</font>");
//            rd.include(request, response);
//        }

//    }



