package com.virtualpet.exeption.sub;

import com.virtualpet.model.Sub;

public class SubNotEnoughMoneyException extends RuntimeException{
    public SubNotEnoughMoneyException(Sub sub, long moneyYouNeed){
        super(String.format("Sub %s don't have enough money %d but need %d", sub.getName(), sub.getCurrency().getMoney(), moneyYouNeed));
    }

    public SubNotEnoughMoneyException(String name, long currentMoney, long moneyYouNeed){
        super(String.format("Sub %s don't have enough money %d but need %d", name, currentMoney, moneyYouNeed));
    }
}
