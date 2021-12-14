package com.virtualpet.exeption.sub;

public class SubTypeNotFoundException extends RuntimeException{

    public SubTypeNotFoundException(String subtypeName){
        super(String.format("Sub type with name: %s not found", subtypeName));
    }
}
