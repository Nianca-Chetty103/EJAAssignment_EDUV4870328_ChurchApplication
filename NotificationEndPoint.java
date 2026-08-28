/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejaassignment.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Question 3: WebSocket endpoint used to push notifications to clients.
 */
@ServerEndpoint("/notifications")
public class NotificationEndPoint {

    private static final Logger LOGGER = Logger.getLogger(NotificationEndPoint.class.getName());
    private static final Set<Session> SESSIONS = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        SESSIONS.add(session);
        LOGGER.log(Level.INFO, "WebSocket opened: {0}", session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        SESSIONS.remove(session);
        LOGGER.log(Level.INFO, "WebSocket closed: {0}", session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        SESSIONS.remove(session);
        LOGGER.log(Level.WARNING, "WebSocket error", throwable);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // Messages sent by a client are relayed to everyone else
        broadcastMessage(message);
    }

    /** Sends the given notification to every connected client. */
    public static void broadcastMessage(String message) {
        for (Session session : SESSIONS) {
            if (session.isOpen()) {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException ex) {
                    LOGGER.log(Level.WARNING, "Could not send message to " + session.getId(), ex);
                }
            }
        }
    }
}
