package com.keyin.finalsprint;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.keyin.finalsprint.User.AuthResponse;
import com.keyin.finalsprint.User.LoginRequest;
import com.keyin.finalsprint.User.SessionInfo;
import com.keyin.finalsprint.Entity.UserEntity;
import com.keyin.finalsprint.Service.SessionService;
import com.keyin.finalsprint.Service.UserService;
import com.keyin.finalsprint.Controller.AuthController;

public class AuthControllerTest2 {

    @Test
    public void testLogin_Successful() {
        // Mock login request
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("testPassword");

        // Mock user in the database
        UserEntity user = new UserEntity();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        // Mock UserService behavior
        UserService userService = mock(UserService.class);
        when(userService.findByUsername("testUser")).thenReturn(java.util.Optional.of(user));

        // Mock session creation
        SessionService sessionService = mock(SessionService.class);
        SessionInfo sessionInfo = new SessionInfo("dummy_token", user);
        when(sessionService.createSession(user)).thenReturn(sessionInfo);

        // Create AuthController instance
        AuthController authController = new AuthController(userService, sessionService);

        // Call the endpoint
        ResponseEntity<?> responseEntity = authController.login(loginRequest);

        // Debugging logs
        System.out.println("Response Status Code: " + responseEntity.getStatusCodeValue());
        System.out.println("Response Body: " + responseEntity.getBody());

        // Assert response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("dummy_token", ((AuthResponse) responseEntity.getBody()).getToken());
        assertEquals(user, ((AuthResponse) responseEntity.getBody()).getUser());

        // Verify that userService.findByUsername and sessionService.createSession are called
        verify(userService, times(1)).findByUsername("testUser");
        verify(sessionService, times(1)).createSession(user);
    }
}
