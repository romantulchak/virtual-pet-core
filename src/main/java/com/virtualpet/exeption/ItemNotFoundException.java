package com.virtualpet.exeption;

import com.virtualpet.model.Item;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(long itemId){
        super(String.format("Item with id: %d not found or not dressed", itemId));
    }
    public ItemNotFoundException(){
        super("Item not found");
    }
}
