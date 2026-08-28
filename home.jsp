<%-- 
    Document   : home
    Created on : 26 Aug 2026, 11:23:34
    Author     : nianc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.ejaassignment.model.User"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Home - City Church</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="page">
            <div class="topbar"></div>
            <div class="content">
                <h1>City Church</h1>
                <h2>Home</h2>

                <p class="success">Welcome, <%= user.getUsername() %> (<%= user.getRole() %>)</p>

                <a class="btn" href="notification.jsp">View Notifications</a>

                <% if ("LEADER".equals(user.getRole())) { %>
                    <br><a class="btn" href="sendNotification.jsp">Server - side</a>
                <% } %>
                <br><a class="btn" href="LogoutServlet">Logout</a>
            </div>
        </div>
    </body>
</html>
