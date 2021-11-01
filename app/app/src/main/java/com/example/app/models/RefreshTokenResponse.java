package com.example.app.models;

public class RefreshTokenResponse {
    String token_refresh;
    boolean success;
    String token;

    public String getToken() {
        return token;
    }

    public String getToken_refresh() {
        return token_refresh;
    }
}