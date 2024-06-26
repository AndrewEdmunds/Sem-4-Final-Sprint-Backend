package com.keyin.finalsprint.User;

import com.keyin.finalsprint.Entity.*;

public class SessionInfo {
    private String token;
    private UserEntity user;

    public SessionInfo(String token, UserEntity user) {
        this.token = token;
        this.user = user;
    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
