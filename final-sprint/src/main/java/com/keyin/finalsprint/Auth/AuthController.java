package com.keyin.finalsprint.Auth;

import com.keyin.finalsprint.User.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;


@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        // Validate user input
        if (!isValidSignupRequest(signupRequest)) {
            return ResponseEntity.badRequest().body("Invalid signup request");
        }

        // Check if username and email are unique
        if (!userService.isUsernameUnique(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        if (!userService.isEmailUnique(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        // Create user entity
        UserEntity user = new UserEntity();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword()); // Set password directly without hashing

        // Save user to the database
        userService.saveUser(user);

        return ResponseEntity.ok().body("{\"message\": \"User signed up successfully\"}");
    }

    private boolean isValidSignupRequest(SignupRequest signupRequest) {
        // Check if signupRequest is not null
        if (signupRequest == null) {
            return false;
        }

        // Check if username is not null or empty
        if (signupRequest.getUsername() == null || signupRequest.getUsername().isEmpty()) {
            return false;
        }

        // Check if email is not null or empty
        if (signupRequest.getEmail() == null || signupRequest.getEmail().isEmpty()) {
            return false;
        }

        // Check if password is not null or empty
        if (signupRequest.getPassword() == null || signupRequest.getPassword().isEmpty()) {
            return false;
        }

        // Add more validation checks if needed

        // All validation checks passed
        return true;
    }
}
