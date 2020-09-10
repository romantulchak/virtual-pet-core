package com.virtualpet.services;

import com.virtualpet.payload.request.LoginRequest;
import com.virtualpet.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
    ResponseEntity<?> registerUser(SignupRequest signupRequest);
}
