package com.virtualpet.service;

import com.virtualpet.payload.request.LoginRequest;
import com.virtualpet.payload.request.SignupRequest;
import com.virtualpet.payload.response.JwtRefreshResponse;
import com.virtualpet.payload.response.JwtResponse;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

    /**
     * Authenticate user by username and password
     *
     * @param loginRequest contains username and password for user
     * @return jwt token with username and roles
     */
    JwtResponse authenticateUser(LoginRequest loginRequest);

    /**
     * Register user by username, email, password
     *
     * @param signupRequest contains username, email, password
     */
    void registerUser(SignupRequest signupRequest);

    /**
     * Refresh token for user
     *
     * @param request to get username from HEADER
     * @return Refresh token with username
     */
    JwtRefreshResponse refreshToken(HttpServletRequest request);
}
