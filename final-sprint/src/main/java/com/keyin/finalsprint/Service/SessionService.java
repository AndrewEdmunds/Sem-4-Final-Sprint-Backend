package com.keyin.finalsprint.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.keyin.finalsprint.Entity.UserEntity;
import com.keyin.finalsprint.User.SessionInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class SessionService {

    private final Logger logger = LoggerFactory.getLogger(SessionService.class);

    // Map to store active sessions (token -> user)
    private final Map<String, UserEntity> activeSessions = new HashMap<>();

    // Method to validate if a session token is active
    public boolean isValidSession(String token) {
        return activeSessions.containsKey(token);
    }

    // Method to retrieve user information based on session token
    public UserEntity getUserFromToken(String token) {
        return activeSessions.get(token);
    }

    // Method to create a new session for a user
    public SessionInfo createSession(UserEntity user) {
        String token = generateToken();
        activeSessions.put(token, user);
        logger.info("Session created for user '{}'", user.getUsername()); // Add this log statement
        return new SessionInfo(token, user);
    }

// Method to remove a session (user logout)
public void removeSession(String token) {
    UserEntity user = activeSessions.remove(token);
    if (user != null) {
        logger.info("Session removed for user '{}'", user.getUsername()); // Add this log statement
    }
}

    // Method to retrieve session info based on token
    public SessionInfo getSessionInfo(String token) {
        if (isValidSession(token)) {
            UserEntity user = getUserFromToken(token);
            if (user != null) {
                logger.info("Session info retrieved for user '{}'", user.getUsername()); // Add this log statement
            } else {
                logger.warn("No user found for token '{}'", token); // Add this log statement
            }
            return new SessionInfo(token, user);
        } else {
            logger.warn("Invalid session token '{}'", token); // Add this log statement
            return null;
        }
    }

    // Method to generate a session token
    private String generateToken() {
        // this is where I would put true token generation logic if that was the route i took
        return "dummy_token_";
    }
}
