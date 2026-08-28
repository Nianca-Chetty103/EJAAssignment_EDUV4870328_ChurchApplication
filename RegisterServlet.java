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
import java.io.IOException;

/**
 * Question 1: User Registration Servlet.
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1.1 Accept username and password from the HTML form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // 1.2 Validate the input data
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            forwardError(request, response, "Username and password are required.");
            return;
        }
        username = username.trim();
        if (username.length() > 30 || password.length() < 4) {
            forwardError(request, response,
                    "Username must be 30 characters or fewer and password at least 4 characters.");
            return;
        }
        if (role == null || !(role.equals("LEADER") || role.equals("MEMBER"))) {
            forwardError(request, response, "Please select a valid user role.");
            return;
        }

        // 1.4 Prevent registration if the username already exists
        if (UserStore.exists(username)) {
            forwardError(request, response, "Username '" + username + "' is already taken.");
            return;
        }

        // 1.3 Store the credentials in the in-memory data structure
        boolean added = UserStore.addUser(new User(username, password, role));

        // 1.5 Return an appropriate success or error response
        if (added) {
            request.setAttribute("message", "Registration successful. Please log in.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            forwardError(request, response, "Registration failed, the username already exists.");
        }
    }

    private void forwardError(HttpServletRequest request, HttpServletResponse response, String error)
            throws ServletException, IOException {
        request.setAttribute("error", error);
        request.getRequestDispatcher("registration.jsp").forward(request, response);
    }
}
