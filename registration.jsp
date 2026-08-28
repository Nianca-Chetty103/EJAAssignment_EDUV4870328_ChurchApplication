<%-- 
    Document   : registration
    Created on : 26 Aug 2026, 11:23:19
    Author     : nianc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registration - City Church Notification System</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="page">
            <div class="topbar"></div>
            <div class="content">
                <h1>City Church Notification System</h1>
                <h2>Registration</h2>

                <% String error = (String) request.getAttribute("error");
                   if (error != null) { %>
                       <p class="error"><%= error %></p>
                <% } %>

                <form action="RegisterServlet" method="post">
                    <input type="text" name="username" placeholder="Username">
                    <input type="password" name="password" placeholder="Password">
                    <select name="role">
                        <option value="MEMBER">Church Member</option>
                        <option value="LEADER">Church Leader</option>
                    </select>
                    <button type="submit">Register</button>
                </form>
            </div>
        </div>
    </body>
</html>
