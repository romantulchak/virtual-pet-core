package com.virtualpet.projection;

import com.virtualpet.model.sub.Currency;
import com.virtualpet.model.sub.Money;

public interface SubMoneyCurrencyProjection {

    Money getMoney();

    Currency getCurrency();

}
