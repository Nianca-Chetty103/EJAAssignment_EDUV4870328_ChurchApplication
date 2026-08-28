<%-- 
    Document   : notification
    Created on : 26 Aug 2026, 11:23:51
    Author     : nianc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.ejaassignment.model.User"%>
<%@page import="com.mycompany.ejaassignment.util.UserStore"%>
<%@page import="java.util.List"%>
<%
    // Role-based access control: only Church Leaders may view this page.
    User current = (User) session.getAttribute("user");
    if (current == null) { response.sendRedirect("login.jsp"); return; }
    if (!"LEADER".equals(current.getRole())) {
        response.sendError(HttpServletResponse.SC_FORBIDDEN,
                "Access denied: only Church Leaders can view notifications.");
        return;
    }
    List<String> notifications = UserStore.getNotifications();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Notification - City Church</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="page">
            <div class="topbar"></div>
            <div class="content">
                <h1>City Church</h1>
                <h2>Notification</h2>

                <p class="label-left">Notifications</p>
                <div class="notice-box" id="box">
                    <% if (notifications.isEmpty()) { %>
                        No notifications yet
                    <% } else {
                         for (String n : notifications) { %>
                        <p><%= n %></p>
                    <%   }
                       } %>
                </div>

                <a class="btn" href="home.jsp">Back to Home</a>
            </div>
        </div>

        <script>
            // Live updates pushed from the WebSocket endpoint
            var ws = new WebSocket((location.protocol === "https:" ? "wss://" : "ws://")
                    + location.host + "${pageContext.request.contextPath}/notifications");
            ws.onmessage = function (event) {
                var box = document.getElementById("box");
                var p = document.createElement("p");
                p.textContent = event.data;
                box.appendChild(p);
            };
        </script>
    </body>
</html>
