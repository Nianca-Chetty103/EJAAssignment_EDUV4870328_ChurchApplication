/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejaassignment.jms;

import com.mycompany.ejaassignment.util.UserStore;
import com.mycompany.ejaassignment.websocket.NotificationEndPoint;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Question 5: Integration with WebSockets.
 *
 * A message-driven bean consumes notifications from the JMS topic and
 * broadcasts each one to every connected WebSocket client.
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup",
            propertyValue = JMSConfig.NOTIFICATION_TOPIC),
    @ActivationConfigProperty(propertyName = "destinationType",
            propertyValue = "javax.jms.Topic"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode",
            propertyValue = "Auto-acknowledge")
})
public class NotificationMDB implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(NotificationMDB.class.getName());

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                String text = ((TextMessage) message).getText();
                LOGGER.log(Level.INFO, "Notification received from JMS: {0}", text);

                // Persist in the in-memory store so home/notification pages can show it
                UserStore.addNotification(text);

                // Broadcast to all connected WebSocket clients
                NotificationEndPoint.broadcastMessage(text);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to process JMS notification", ex);
        }
    }
}
