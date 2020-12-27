package com.virtualpet.exeption;

public class SubWithNameAlreadyExistException extends RuntimeException{
    public SubWithNameAlreadyExistException(String subName){
        super(String.format("Sub with name: %s already exist", subName));
    }
}
