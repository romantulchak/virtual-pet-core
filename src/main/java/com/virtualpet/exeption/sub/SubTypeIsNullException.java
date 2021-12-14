package com.virtualpet.exeption.sub;

public class SubTypeIsNullException extends RuntimeException{
    public SubTypeIsNullException(){
        super("Sub type is null");
    }
}
