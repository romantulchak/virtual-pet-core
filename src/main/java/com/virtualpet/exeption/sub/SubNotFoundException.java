package com.virtualpet.exeption.sub;

public class SubNotFoundException extends RuntimeException{
    public SubNotFoundException(long subId){
        super(String.format("Sub with id: %d not found", subId));
    }
    public SubNotFoundException(){
        super("Sub not found");
    }
}
