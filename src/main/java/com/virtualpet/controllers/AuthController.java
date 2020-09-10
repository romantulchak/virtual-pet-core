package com.virtualpet.controllers;

import com.virtualpet.payload.request.LoginRequest;
import com.virtualpet.payload.request.SignupRequest;
import com.virtualpet.services.AuthService;
import com.virtualpet.services.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*", maxAge = 3600L)
@RequestMapping("/api/auth")
public class AuthController {

    private AuthServiceImpl authService;
    @Autowired
    public AuthController(AuthServiceImpl authService){
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest){
        return authService.registerUser(signupRequest);
    }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){
        return authService.authenticateUser(loginRequest);
    }





}
