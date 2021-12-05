package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Views;
import com.virtualpet.model.sub.Currency;
import com.virtualpet.model.sub.Money;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MoneyCurrencyDTO {

    @JsonView({Views.MoneyCurrencyView.class})
    private Money money;

    @JsonView({Views.SubView.class, Views.MoneyCurrencyView.class})
    private Currency currency;

    public MoneyCurrencyDTO(){

    }
}
