package com.virtualpet.exeption;

public class ShopNotFoundException  extends RuntimeException{
    public ShopNotFoundException(){
        super("Shop not found");
    }
}
