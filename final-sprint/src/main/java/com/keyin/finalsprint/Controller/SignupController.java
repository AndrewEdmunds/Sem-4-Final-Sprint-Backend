package com.keyin.finalsprint.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.keyin.finalsprint.Entity.UserEntity;
import com.keyin.finalsprint.Service.UserService;
import com.keyin.finalsprint.User.SignupRequest;

@RestController
@RequestMapping("/api")
public class SignupController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignupRequest signupRequest, RedirectAttributes redirectAttributes) {
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

        // Create a new user entity
        UserEntity newUser = new UserEntity();
        newUser.setUsername(signupRequest.getUsername());
        newUser.setEmail(signupRequest.getEmail());
        newUser.setPassword(signupRequest.getPassword());

        // Save the new user to the database
        userService.saveUser(newUser);

        // Add a success flash attribute for redirection
        redirectAttributes.addFlashAttribute("successMessage", "User Created Successfully");

        // Redirect to the login page
        return ResponseEntity.status(HttpStatus.CREATED).body("User Created Successfully");
    }

    private boolean isValidSignupRequest(SignupRequest signupRequest) {
        return signupRequest != null && signupRequest.getUsername() != null && signupRequest.getEmail() != null && signupRequest.getPassword() != null;
    }
}