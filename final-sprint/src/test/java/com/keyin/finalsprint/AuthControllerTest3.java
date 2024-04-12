package com.keyin.finalsprint;

import com.keyin.finalsprint.Entity.UserEntity;
import com.keyin.finalsprint.Service.SessionService;
import com.keyin.finalsprint.User.SessionInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthControllerTest3 {

    private SessionService sessionService;

    @BeforeEach
    public void setUp() {
        sessionService = new SessionService();
    }

    @Test
    public void testIsValidSession_ValidToken() {
        String token = "dummy_token_";
        sessionService.createSession(new UserEntity());

        assertTrue(sessionService.isValidSession(token), "Valid token should return true");
        System.out.println("isValidSession_ValidToken Test Passed");
    }

    @Test
    public void testIsValidSession_InvalidToken() {
        assertFalse(sessionService.isValidSession("invalid_token"), "Invalid token should return false");
        System.out.println("isValidSession_InvalidToken Test Passed");
    }

    @Test
    public void testGetUserFromToken_ValidToken() {
        UserEntity user = new UserEntity();
        SessionInfo sessionInfo = sessionService.createSession(user);

        UserEntity retrievedUser = sessionService.getUserFromToken(sessionInfo.getToken());
        
        assertNotNull(retrievedUser, "Retrieved user should not be null");
        assertEquals(user, retrievedUser, "Retrieved user should match the added user");
        System.out.println("getUserFromToken_ValidToken Test Passed");
    }

    @Test
    public void testGetUserFromToken_InvalidToken() {
        UserEntity retrievedUser = sessionService.getUserFromToken("invalid_token");

        assertNull(retrievedUser, "Retrieved user should be null for an invalid token");
        System.out.println("getUserFromToken_InvalidToken Test Passed");
    }

    @Test
    public void testGetSessionInfo_ValidToken() {
        UserEntity user = new UserEntity();
        SessionInfo sessionInfo = sessionService.createSession(user);

        String token = sessionInfo.getToken();
        SessionInfo retrievedSessionInfo = sessionService.getSessionInfo(token);

        assertNotNull(retrievedSessionInfo, "Retrieved SessionInfo should not be null");
        assertEquals(token, retrievedSessionInfo.getToken(), "Retrieved token should match the added token");
        assertEquals(user, retrievedSessionInfo.getUser(), "Retrieved user should match the added user");
        System.out.println("getSessionInfo_ValidToken Test Passed");
    }

    @Test
    public void testGetSessionInfo_InvalidToken() {
        SessionInfo sessionInfo = sessionService.getSessionInfo("invalid_token");

        assertNull(sessionInfo, "Retrieved SessionInfo should be null for an invalid token");
        System.out.println("getSessionInfo_InvalidToken Test Passed");
    }
}
