package com.virtualpet.exeption;

public class UserAuthenticationException extends RuntimeException{
    public UserAuthenticationException(){
        super("Authentication required");
    }
}
