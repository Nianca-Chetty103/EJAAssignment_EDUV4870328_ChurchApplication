/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.ejaassignment.servlet;

import com.mycompany.ejaassignment.model.User;
import com.mycompany.ejaassignment.util.UserStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Question 2: Login 
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 2.1 Accept username and password from the HTML form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 2.2 Validate the input data
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            forwardError(request, response, "Username and password are required.");
            return;
        }

        User user = UserStore.validate(username.trim(), password);
        if (user == null) {
            // 2.4 Error response
            forwardError(request, response, "Invalid username or password.");
            return;
        }

        // 2.3 Create an HTTP session and store the authenticated user
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);
        session.setAttribute("role", user.getRole());
        session.setMaxInactiveInterval(30 * 60);

        // 2.4 Success response
        response.sendRedirect("home.jsp");
    }

    private void forwardError(HttpServletRequest request, HttpServletResponse response, String error)
            throws ServletException, IOException {
        request.setAttribute("error", error);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
