package com.keyin.finalsprint.Controller;

import com.keyin.finalsprint.User.LoginRequest;
import com.keyin.finalsprint.Entity.UserEntity;
import com.keyin.finalsprint.Service.SessionService;
import com.keyin.finalsprint.Service.UserService;
import com.keyin.finalsprint.User.AuthResponse;
import com.keyin.finalsprint.User.SessionInfo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);


    private final UserService userService;
    private final SessionService sessionService;

    @Autowired
    public AuthController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Validate user input
        if (!isValidLoginRequest(loginRequest)) {
            return ResponseEntity.badRequest().body("Invalid login request");
        }

        // Check if the provided credentials match a user in the database
        UserEntity user = userService.findByUsername(loginRequest.getUsername()).orElse(null);
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            // Authentication successful
            SessionInfo sessionInfo = sessionService.createSession(user);
            logger.info("User '{}' logged in", user.getUsername());
            return ResponseEntity.ok().body(new AuthResponse(sessionInfo.getToken(), sessionInfo.getUser()));
        } else {
            // Authentication failed
            logger.warn("Login failed for user '{}'", loginRequest.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authorizationHeader) {
    try {
        // Extract the token from the Authorization header
        String token = extractToken(authorizationHeader);

        // Remove the session associated with the token
        sessionService.removeSession(token);

        // Log successful logout
        logger.info("User logged out");

        return ResponseEntity.ok().body("Logout successful");
    } catch (Exception e) {
        // Log error during logout
        logger.error("Error during logout:", e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error logging out");
    }
}
    

    @GetMapping("/status")
    public ResponseEntity<?> checkAuthStatus(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            // Extract the token from the Authorization header
            String token = extractToken(authorizationHeader);
    
            // Log the token
            logger.info("Received token for authentication status check: {}", token);
    
            // Check if the provided token is valid
            SessionInfo sessionInfo = sessionService.getSessionInfo(token);
            if (sessionInfo != null) {
                // If the session is valid, retrieve user information associated with the token
                UserEntity user = sessionInfo.getUser();
                // Log the user information for debugging purposes
                logger.info("Authenticated user: {}", user.getUsername());
                // Return authentication status and user information
                return ResponseEntity.ok().body(new AuthResponse(token, user));
            } else {
                // If the session is not valid or the token is invalid, return authentication status false
                logger.warn("Invalid token provided for authentication status check: {}", token);
                return ResponseEntity.ok().body(new AuthResponse(null, null));
            }
        } catch (Exception e) {
            // Log any exceptions that occur during the authentication status check
            logger.error("Error checking authentication status: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error checking authentication status");
        }
    }
    
    private String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // Remove the "Bearer " prefix
        }
        return null;
    }

    // custom class to represent the response
    static class AuthStatusResponse {
        private boolean isAuthenticated;
        private String username;

        public AuthStatusResponse(boolean isAuthenticated, String username) {
            this.isAuthenticated = isAuthenticated;
            this.username = username;
        }

        // Getters and setters
        public boolean isAuthenticated() {
            return isAuthenticated;
        }

        public void setAuthenticated(boolean authenticated) {
            isAuthenticated = authenticated;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    private boolean isValidLoginRequest(LoginRequest loginRequest) {
        return loginRequest != null && loginRequest.getUsername() != null && loginRequest.getPassword() != null;
    }
}

