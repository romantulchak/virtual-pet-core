package com.virtualpet.model.sub;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Views;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class Money {

    @JsonView({Views.MoneyCurrencyView.class})
    private int moneyMultiplier;

    @JsonView({Views.MoneyCurrencyView.class})
    private int moneyUpLevel;

    @JsonView({Views.MoneyCurrencyView.class})
    private long moneyUpPrice;

    public Money() {
        this.moneyUpLevel = 1;
        this.moneyUpPrice = 50;
        this.moneyMultiplier = 5;
    }


}
