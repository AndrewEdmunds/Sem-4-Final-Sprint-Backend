package com.keyin.finalsprint.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.keyin.finalsprint.Entity.UserEntity;
import com.keyin.finalsprint.Service.UserService;
import com.keyin.finalsprint.Service.SessionService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final SessionService sessionService;

    @Autowired
    public UserController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String token) {
        // Check if the provided token is valid
        if (sessionService.isValidSession(token)) {
            // If the session is valid, retrieve user information associated with the token
            UserEntity user = sessionService.getUserFromToken(token);
            return ResponseEntity.ok(user);
        } else {
            // If the session is not valid or the token is invalid, return unauthorized status
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }
    }
}
