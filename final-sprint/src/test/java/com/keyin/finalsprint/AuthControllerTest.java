package com.keyin.finalsprint;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.keyin.finalsprint.Controller.AuthController;
import com.keyin.finalsprint.Service.SessionService;
import com.keyin.finalsprint.User.AuthResponse;
import com.keyin.finalsprint.User.SessionInfo;
import com.keyin.finalsprint.Entity.UserEntity;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {

    @Mock
    private SessionService sessionService;

    @InjectMocks
    private AuthController authController;

    @Test
    public void testCheckAuthStatus_ValidToken() {
        SessionInfo sessionInfo = new SessionInfo("dummy_token", new UserEntity());
        when(sessionService.getSessionInfo(anyString())).thenReturn(sessionInfo);

        System.out.println("Calling checkAuthStatus with token: dummy_token");
        ResponseEntity<?> responseEntity = authController.checkAuthStatus("dummy_token");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        AuthResponse responseBody = (AuthResponse) responseEntity.getBody();
        assertNotNull(responseBody);
        assertNotNull(responseBody.getToken());
        assertNotNull(responseBody.getUser());
        System.out.println("Response body: " + responseBody);
        
        System.out.println("Session info: " + sessionInfo.getUser());
        System.out.println(sessionService.getSessionInfo("dummy_token_")); 
    }

    @Test
    public void testCheckAuthStatus_InvalidToken() {
        when(sessionService.getSessionInfo(anyString())).thenReturn(null);

        System.out.println("Calling checkAuthStatus with invalid token");
        ResponseEntity<?> responseEntity = authController.checkAuthStatus("invalid_token");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        AuthResponse responseBody = (AuthResponse) responseEntity.getBody();
        assertNotNull(responseBody);
        assertNull(responseBody.getToken());
        assertNull(responseBody.getUser());
        System.out.println("Response body: " + responseBody);
        
        System.out.println("Session info: null");
    }
}
