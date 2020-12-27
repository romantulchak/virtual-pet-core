package com.virtualpet.exeption;

import com.virtualpet.model.Sub;

public class SubNotEnoughMoneyException extends RuntimeException{
    public SubNotEnoughMoneyException(Sub sub, long moneyYouNeed){
        super(String.format("Sub %s don't have enough money %d but need %d", sub.getName(), sub.getCurrency().getMoney(), moneyYouNeed));
    }
}
