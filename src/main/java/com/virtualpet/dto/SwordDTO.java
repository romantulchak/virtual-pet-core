package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Item;
import com.virtualpet.model.Views;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@JsonTypeName("swordItem")
@Data
public class SwordDTO extends Item {

    @JsonView({Views.ShopView.class, Views.SubView.class})
    private int attack;

    @JsonView({Views.ShopView.class, Views.SubView.class})
    private boolean allowShield;

    @JsonView({Views.ShopView.class, Views.SubView.class})
    private boolean isBought;

}

