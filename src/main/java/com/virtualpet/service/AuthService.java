package com.virtualpet.service;

import com.virtualpet.payload.request.LoginRequest;
import com.virtualpet.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
    void registerUser(SignupRequest signupRequest);
}
