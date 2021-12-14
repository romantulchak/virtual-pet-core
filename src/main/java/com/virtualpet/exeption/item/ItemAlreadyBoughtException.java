package com.virtualpet.exeption.item;

public class ItemAlreadyBoughtException extends RuntimeException{
    public ItemAlreadyBoughtException(String itemName){
        super(String.format("Item %s already bought", itemName));
    }
}
