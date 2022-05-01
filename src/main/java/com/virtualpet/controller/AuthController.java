package com.virtualpet.controller;

import com.virtualpet.payload.request.LoginRequest;
import com.virtualpet.payload.request.SignupRequest;
import com.virtualpet.payload.response.JwtRefreshResponse;
import com.virtualpet.payload.response.JwtResponse;
import com.virtualpet.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(value = "*", maxAge = 3600L)
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping("/sign-up")
    public void registerUser(@RequestBody SignupRequest signupRequest){
        authService.registerUser(signupRequest);
    }

    @PostMapping("/sign-in")
    public JwtResponse authenticateUser(@RequestBody LoginRequest loginRequest){
        return authService.authenticateUser(loginRequest);
    }

    @GetMapping("/refresh-token")
    public JwtRefreshResponse authenticateUser(HttpServletRequest request){
        return authService.refreshToken(request);
    }


}
