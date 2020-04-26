package controller;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LogInServlet extends HttpServlet {
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
    private static final long serialVersionUID = 1L;

    public LogInServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/login.jsp");

        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);

        User user = null;
        boolean hasError = false;
        String errorString = null;

        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            UserService us = new UserService();
            user = us.getUser(userName, password);
            if (user == null) {
                hasError = true;
                errorString = "User Name or password invalid";
            }

        }
        if (hasError) {
            user = new User();
            user.setName(userName);
            user.setPassword(password);

            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);

            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("login.jsp");

            dispatcher.forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("loginedUser", user);

            // If user checked "Remember me".
            if (remember) {
                System.out.println("Store user cookie");
                Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getName());
                cookieUserName.setMaxAge(24 * 60 * 60);
                response.addCookie(cookieUserName);
            }
            // Else delete cookie.
            else {
                Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
                cookieUserName.setMaxAge(0);
                response.addCookie(cookieUserName);
            }

            // Redirect to flights page.
            response.sendRedirect(request.getContextPath() + "/flights");
        }
    }
}
