package com.virtualpet.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {

    private long id;

    private String token;

    private String type = "Bearer";

    private String username;

    private String email;

    private List<String> roles;

    public JwtResponse(String jwt, long id, String username, String email, List<String> roles) {
        this.token = jwt;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
