package com.virtualpet.exeption;

public class UserFriendNotFoundException extends RuntimeException{
    public UserFriendNotFoundException(long userFriendId){
        super(String.format("Friend request with id: %d not found", userFriendId));
    }
}
