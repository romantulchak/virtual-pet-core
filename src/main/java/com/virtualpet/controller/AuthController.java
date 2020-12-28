package com.virtualpet.controller;

import com.virtualpet.payload.request.LoginRequest;
import com.virtualpet.payload.request.SignupRequest;
import com.virtualpet.payload.response.JwtResponse;
import com.virtualpet.payload.response.MessageResponse;
import com.virtualpet.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public void registerUser(@RequestBody SignupRequest signupRequest){
        authService.registerUser(signupRequest);
    }
    @PostMapping("/signin")
    public JwtResponse authenticateUser(@RequestBody LoginRequest loginRequest){
        return authService.authenticateUser(loginRequest);
    }





}
