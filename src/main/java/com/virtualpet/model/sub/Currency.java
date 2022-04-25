package com.virtualpet.model.sub;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Views;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class Currency {

    @JsonView({Views.SubView.class, Views.MoneyCurrencyView.class, Views.GameSubView.class})
    private long money;

    @JsonView({Views.SubView.class, Views.SubView.class, Views.GameSubView.class})
    private long diamond;

    @JsonView({Views.SubView.class, Views.SubView.class, Views.GameSubView.class})
    private long emerald;

    @JsonView({Views.SubView.class, Views.SubView.class, Views.GameSubView.class})
    private int maxDiamond;

    public Currency() {
        this.money = 0;
        this.diamond = 0;
        this.emerald = 0;
        this.maxDiamond = 300;
    }
}
