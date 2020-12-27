package com.virtualpet.exeption;

public class UsernameAlreadyExistException extends RuntimeException{

        public UsernameAlreadyExistException(String username){
            super(String.format("User with username: %s already exist", username));
        }
}
