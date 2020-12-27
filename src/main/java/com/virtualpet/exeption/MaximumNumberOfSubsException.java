package com.virtualpet.exeption;

public class MaximumNumberOfSubsException extends RuntimeException{

    public MaximumNumberOfSubsException(){
        super("You already have the maximum number of Sub in your account");
    }
}
