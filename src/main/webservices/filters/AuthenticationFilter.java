package main.webservices.filters;

/**
 * Created by Shoma on 23.04.2017.
 */import java.io.IOException;

import javax.servlet.*;

import javax.servlet.http.*;



public class AuthenticationFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        this.context.log("Requested Resource::"+uri);

        HttpSession session = req.getSession();
String user = (String) session.getAttribute("user");
        if(user == null){
            this.context.log("Unauthorized access request");
            res.sendRedirect("/index1.jsp");
        }else{
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }


    }



    public void destroy() {
        //close any resources here
    }

}