package com.keyin.finalsprint;

import com.keyin.finalsprint.Controller.AuthController;
import com.keyin.finalsprint.Entity.UserEntity;
import com.keyin.finalsprint.Service.SessionService;
import com.keyin.finalsprint.Service.UserService;
import com.keyin.finalsprint.User.AuthResponse;
import com.keyin.finalsprint.User.LoginRequest;
import com.keyin.finalsprint.User.SessionInfo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;



import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private SessionService sessionService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLogin_ValidCredentials() {
        // Mock data
        LoginRequest loginRequest = new LoginRequest("username", "password");
        UserEntity userEntity = new UserEntity("username", "password", "email");
    
        // Mock userService.findByUsername()
        when(userService.findByUsername(loginRequest.getUsername())).thenReturn(Optional.of(userEntity));
    
        // Mock sessionService.createSession()
        when(sessionService.createSession(userEntity)).thenReturn(new SessionInfo("token", userEntity));
    
        // Call the controller method
        ResponseEntity<?> responseEntity = authController.login(loginRequest);
    
        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    
        AuthResponse expectedResponse = new AuthResponse("token", userEntity);
        AuthResponse actualResponse = (AuthResponse) responseEntity.getBody();
    
        assertEquals(expectedResponse.getToken(), actualResponse.getToken());
        assertEquals(expectedResponse.getUser(), actualResponse.getUser());
    }
    

    @Test
    void testLogin_InvalidUsername() {
        // Mock data
        LoginRequest loginRequest = new LoginRequest("non_existing_user", "password");

        // Mock userService.findByUsername()
        when(userService.findByUsername(loginRequest.getUsername())).thenReturn(Optional.empty());

        // Call the controller method
        ResponseEntity<?> responseEntity = authController.login(loginRequest);

        // Assertions
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Invalid username or password", responseEntity.getBody());
    }

    @Test
    void testLogin_InvalidPassword() {
        // Mock data
        LoginRequest loginRequest = new LoginRequest("username", "wrong_password");
        UserEntity userEntity = new UserEntity("username", "password", "email");

        // Mock userService.findByUsername()
        when(userService.findByUsername(loginRequest.getUsername())).thenReturn(Optional.of(userEntity));

        // Call the controller method
        ResponseEntity<?> responseEntity = authController.login(loginRequest);

        // Assertions
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Invalid username or password", responseEntity.getBody());
    }

    @Test
    void testLogout() {
        // Mock data
        String authorizationHeader = "Bearer token";

        // Call the controller method
        ResponseEntity<?> responseEntity = authController.logout(authorizationHeader);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Logout successful", responseEntity.getBody());
        verify(sessionService, times(1)).removeSession("token");
    }

    @Test
    void testCheckAuthStatus_ValidToken() {
        // Mock data
        String authorizationHeader = "Bearer token";
        UserEntity userEntity = new UserEntity("username", "password", "email");
    
        // Mock sessionService.getSessionInfo()
        when(sessionService.getSessionInfo("token")).thenReturn(new SessionInfo("token", userEntity));
    
        // Call the controller method
        ResponseEntity<?> responseEntity = authController.checkAuthStatus(authorizationHeader);
        System.out.println("hello" + authorizationHeader);
    
        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    
        AuthResponse expectedResponse = new AuthResponse("token", userEntity);
        AuthResponse actualResponse = (AuthResponse) responseEntity.getBody();
    
        assertEquals(expectedResponse.getToken(), actualResponse.getToken());
        assertEquals(expectedResponse.getUser(), actualResponse.getUser());
    }

    @Test
    void testCheckAuthStatus_InvalidToken() {
        // Mock data
        String authorizationHeader = "";

        // Call the controller method
        ResponseEntity<?> responseEntity = authController.checkAuthStatus(authorizationHeader);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
        assertNull(responseEntity.getBody());
        
    }
}

