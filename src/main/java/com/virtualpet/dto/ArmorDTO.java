package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Item;
import com.virtualpet.model.Views;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@JsonTypeName("armorItem")
@Data
public class ArmorDTO extends Item {

    @JsonView({Views.ShopView.class, Views.SubView.class})
    private int armor;

    @JsonView({Views.ShopView.class, Views.SubView.class})
    private int health;

    @JsonView({Views.ShopView.class})
    private boolean isBought;

}
