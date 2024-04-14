package com.keyin.finalsprint.User;

import com.keyin.finalsprint.Entity.UserEntity;

public class AuthResponse {
    private String token;
    private UserEntity user;

    // Constructor with token and user
    public AuthResponse(String token, UserEntity user) {
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
