package com.virtualpet.exeption.item;

public class ItemCategoryNotFoundException extends RuntimeException {

    public ItemCategoryNotFoundException() {
        super("Category item not found");
    }

}
