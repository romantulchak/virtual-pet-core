package com.virtualpet.service;

import com.virtualpet.payload.request.LoginRequest;
import com.virtualpet.payload.request.SignupRequest;
import com.virtualpet.payload.response.JwtRefreshResponse;
import com.virtualpet.payload.response.JwtResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    JwtResponse authenticateUser(LoginRequest loginRequest);
    void registerUser(SignupRequest signupRequest);
    JwtRefreshResponse refreshToken(HttpServletRequest request);
}
