package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Item;
import com.virtualpet.model.Sub;
import com.virtualpet.model.Views;
import com.virtualpet.model.items.Sword;
@JsonTypeName("swordItem")
public class SwordDTO extends Item {


    @JsonView({Views.ShopView.class, Views.SubView.class})
    private int attack;

    @JsonView({Views.ShopView.class, Views.SubView.class})
    private boolean allowShield;

    @JsonView({Views.ShopView.class, Views.SubView.class})
    private boolean isBought;

    public SwordDTO() {
    }

    public SwordDTO(Sword sword) {
        super(sword.getId(),sword.getUniqueness(), sword.getIconPath(), sword.getName(), sword.getItemCategory(), sword.getItemType(), sword.getPrice());
        this.allowShield = sword.isShieldAllowed();
        this.attack = sword.getAttack();
    }
    public SwordDTO(Sword sword, Sub sub) {
        super(sword.getId(),sword.getUniqueness(), sword.getIconPath(), sword.getName(), sword.getItemCategory(), sword.getItemType(), sword.getPrice());
        this.allowShield = sword.isShieldAllowed();
        this.attack = sword.getAttack();
        this.isBought = sub.getInventory().getItems().contains(sword);
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public boolean isAllowShield() {
        return allowShield;
    }

    public void setAllowShield(boolean allowShield) {
        this.allowShield = allowShield;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }
}

