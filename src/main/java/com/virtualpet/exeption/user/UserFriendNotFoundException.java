package com.virtualpet.exeption.user;

public class UserFriendNotFoundException extends RuntimeException{
    public UserFriendNotFoundException(long userFriendId){
        super(String.format("Friend request with id: %d not found", userFriendId));
    }
}
