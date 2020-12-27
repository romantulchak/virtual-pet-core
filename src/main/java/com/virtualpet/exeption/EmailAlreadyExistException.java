package com.virtualpet.exeption;

public class EmailAlreadyExistException extends RuntimeException{
    public EmailAlreadyExistException(String email){
        super(String.format("User with email: %s already exist", email));
    }
}
