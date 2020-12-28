package com.virtualpet.exeption;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String username){
        super(String.format("User with username: %s not found", username));
    }
    public UserNotFoundException(){
        super("User not found");
    }

}
