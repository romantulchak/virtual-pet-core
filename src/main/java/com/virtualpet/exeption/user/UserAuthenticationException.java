package com.virtualpet.exeption.user;

public class UserAuthenticationException extends RuntimeException{
    public UserAuthenticationException(){
        super("Authentication required");
    }
}
