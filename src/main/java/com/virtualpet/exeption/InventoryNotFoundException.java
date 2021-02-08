package com.virtualpet.exeption;

public class InventoryNotFoundException extends RuntimeException{
    public InventoryNotFoundException(String subName){
        super(String.format("Sub %s have not an inventory", subName));
    }
}
