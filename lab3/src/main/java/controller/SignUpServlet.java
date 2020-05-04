package controller;

import model.Role;
import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/signUp"})
public class SignUpServlet extends HttpServlet {
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/signup.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get params
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserService us = new UserService();
        User user = us.addUser(email, password, Role.MANAGER);

        // store logined user
        HttpSession session = request.getSession();
        session.setAttribute("loginedUser", user);
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getName());
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);

        // redirect to home page
        response.sendRedirect(request.getContextPath() + "/flights");
    }

    @Override
    public String getServletInfo() {
        return "Sign up servlet";
    }
}