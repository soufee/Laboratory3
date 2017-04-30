package main.webservices.filters;

import main.webservices.listeners.MySessionListener;
import main.webservices.session.LoginServlet;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Shoma on 24.04.2017.
 */
public class AdminFilter implements Filter{

    private static Logger userLogger = Logger.getLogger(MySessionListener.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String userLogin = (String) ((HttpServletRequest) servletRequest)
                .getSession().getAttribute("user");

        if (userLogin != null&&userLogin.equals(LoginServlet.gamer.getNiackname())&&LoginServlet.gamer.isAdmin()) {
           userLogger.debug("Пользователь является админом "+ userLogin);

            filterChain.doFilter(servletRequest, servletResponse);

        } else {
            ((HttpServletResponse) servletResponse)
                    .sendRedirect(((HttpServletRequest) servletRequest).getContextPath() + "/");
            userLogger.debug("Пользователь не является админом");
        }

    }

    @Override
    public void destroy() {

    }
}
