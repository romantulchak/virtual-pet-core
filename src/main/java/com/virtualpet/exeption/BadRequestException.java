package com.virtualpet.exeption;

public class BadRequestException extends RuntimeException{
    public BadRequestException(){
        super("Bad request");
    }
}
