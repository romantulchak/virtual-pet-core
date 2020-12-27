package com.virtualpet.exeption;

public class LevelNotFoundException extends RuntimeException{
    public LevelNotFoundException(){
        super("Level not found");
    }
}
