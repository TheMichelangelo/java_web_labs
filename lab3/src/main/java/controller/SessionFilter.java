package controller;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "SessionFilter", urlPatterns = { "/*" })
public class SessionFilter implements Filter {
    public SessionFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("loginedUser");
        StringBuffer requestUrl=req.getRequestURL();
        String urlAsString=requestUrl.toString();
        if(user==null && urlAsString.endsWith("/login")==false
                 && urlAsString.endsWith("/signUp")==false
                 && urlAsString.endsWith("/")==false)
        {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendRedirect("/");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}
