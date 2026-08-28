/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejaassignment.util;

import com.mycompany.ejaassignment.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * In-memory data structure holding registered users and notifications.
 */
public final class UserStore {

    private static final Map<String, User> USERS = new ConcurrentHashMap<>();
    private static final List<String> NOTIFICATIONS = new CopyOnWriteArrayList<>();

    static {
        // Default accounts for testing
        USERS.put("leader", new User("leader", "leader123", "LEADER"));
        USERS.put("member", new User("member", "member123", "MEMBER"));
    }

    private UserStore() {
    }

    public static boolean exists(String username) {
        return username != null && USERS.containsKey(username.toLowerCase());
    }

    /** @return true when the user was added, false when the username already exists. */
    public static boolean addUser(User user) {
        return USERS.putIfAbsent(user.getUsername().toLowerCase(), user) == null;
    }

    public static User findUser(String username) {
        return username == null ? null : USERS.get(username.toLowerCase());
    }

    public static User validate(String username, String password) {
        User user = findUser(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public static void addNotification(String message) {
        NOTIFICATIONS.add(message);
    }

    public static List<String> getNotifications() {
        return Collections.unmodifiableList(new ArrayList<>(NOTIFICATIONS));
    }
}
