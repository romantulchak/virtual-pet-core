package com.virtualpet.payload.response;

public class JwtRefreshResponse {
    private String username;
    private String token;

    public JwtRefreshResponse(){

    }

    public JwtRefreshResponse(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
