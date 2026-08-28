<%-- 
    Document   : sendNotification.jsp
    Created on : 28 Aug 2026, 08:32:59
    Author     : nianc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.ejaassignment.model.User"%>
<%
    User leader = (User) session.getAttribute("user");
    if (leader == null) { response.sendRedirect("login.jsp"); return; }
    if (!"LEADER".equals(leader.getRole())) {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Only Church Leaders may send notifications");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Send Notification - City Church</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="page">
            <div class="topbar"></div>
            <div class="content">
                <h1>City Church Notification System</h1>
                <h2>Server - side</h2>

                <% String status = (String) request.getAttribute("status");
                   if (status != null) { %><p class="success"><%= status %></p><% } %>

                <form action="SendNotification" method="post">
                    <p class="label-left">Write a notification to send to the library users</p>
                    <textarea name="notification"></textarea>
                    <button type="submit">Send notification</button>
                </form>
                <a class="btn" href="home.jsp">Back to Home</a>
            </div>
        </div>
    </body>
</html>
