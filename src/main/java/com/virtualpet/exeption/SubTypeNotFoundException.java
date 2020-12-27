package com.virtualpet.exeption;

public class SubTypeNotFoundException extends RuntimeException{

    public SubTypeNotFoundException(String subtypeName){
        super(String.format("Sub type with name: %s not found", subtypeName));
    }
}
