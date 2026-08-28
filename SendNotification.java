package com.mycompany.ejaassignment.servlet;

import com.mycompany.ejaassignment.jms.JMSConfig;
import com.mycompany.ejaassignment.model.User;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Church Leaders publish a notification to the JMS destination.
 * The message-driven bean then broadcasts it to WebSocket clients.
 */
@WebServlet(name = "SendNotification", urlPatterns = {"/SendNotification"})
public class SendNotification extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Resource(lookup = JMSConfig.CONNECTION_FACTORY)
    private ConnectionFactory connectionFactory;

    @Resource(lookup = JMSConfig.NOTIFICATION_TOPIC)
    private Topic notificationTopic;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = session == null ? null : (User) session.getAttribute("user");

        // Role based access control - only Church Leaders may send notifications
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        if (!"LEADER".equals(user.getRole())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN,
                    "Access denied: only Church Leaders can send notifications.");
            return;
        }

        String notification = request.getParameter("notification");
        if (notification == null || notification.trim().isEmpty()) {
            request.setAttribute("status", "Please type a notification before sending.");
            request.getRequestDispatcher("sendNotification.jsp").forward(request, response);
            return;
        }

        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            // Persistent delivery gives reliable message persistence
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            producer.send(notificationTopic, notification.trim());
            request.setAttribute("status", "Notification sent successfully.");
        } catch (Exception ex) {
            log("Unable to publish notification", ex);
            request.setAttribute("status", "Notification could not be sent: " + ex.getMessage());
        }

        request.getRequestDispatcher("sendNotification.jsp").forward(request, response);
    }
}
