package com.virtualpet.exeption;

public class NotEnoughMoneyException extends RuntimeException{
    public NotEnoughMoneyException(String subName, long subMoney, int itemPrice){
        super(String.format("Sub %s have %d money but item price is %d", subName, subMoney, itemPrice));
    }
}
