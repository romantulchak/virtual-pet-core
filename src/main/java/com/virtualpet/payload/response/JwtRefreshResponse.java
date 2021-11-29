package com.virtualpet.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtRefreshResponse {
    private String username;
    private String token;

    public JwtRefreshResponse(){

    }
}
