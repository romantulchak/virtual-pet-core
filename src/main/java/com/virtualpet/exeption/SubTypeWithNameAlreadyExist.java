package com.virtualpet.exeption;

public class SubTypeWithNameAlreadyExist extends RuntimeException{
    public SubTypeWithNameAlreadyExist(String name){
        super(String.format("SubType with this name: %s already exist", name));
    }
}
