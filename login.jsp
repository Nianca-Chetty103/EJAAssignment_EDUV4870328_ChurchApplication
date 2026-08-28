<%-- 
    Document   : login
    Created on : 26 Aug 2026, 11:22:39
    Author     : nianc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login - City Church Notification System</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="page">
            <div class="topbar"></div>
            <div class="content">
                <h1>City Church Notification System</h1>
                <h2>Login</h2>

                <% String error = (String) request.getAttribute("error");
                   String message = (String) request.getAttribute("message");
                   if (error != null) { %>
                       <p class="error"><%= error %></p>
                <% } else if (message != null) { %>
                       <p class="success"><%= message %></p>
                <% } %>

                <form action="LoginServlet" method="post">
                    <input type="text" name="username" placeholder="Username">
                    <input type="password" name="password" placeholder="Password">
                    <button type="submit">Login</button>
                </form>

                <a class="btn" href="registration.jsp">Register</a>
            </div>
        </div>
    </body>
</html>
