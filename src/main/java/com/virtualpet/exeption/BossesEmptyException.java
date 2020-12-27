package com.virtualpet.exeption;

public class BossesEmptyException extends RuntimeException{
    public BossesEmptyException(){
        super("Bosses is empty");
    }
}
