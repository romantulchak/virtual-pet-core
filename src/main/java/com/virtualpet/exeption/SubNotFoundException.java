package com.virtualpet.exeption;

public class SubNotFoundException extends RuntimeException{
    public SubNotFoundException(long subId){
        super(String.format("Sub with id: %d not found", subId));
    }
}
