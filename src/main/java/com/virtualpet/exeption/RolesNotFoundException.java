package com.virtualpet.exeption;

public class RolesNotFoundException extends RuntimeException{
    public RolesNotFoundException(){
        super("Roles not found");
    }
}
