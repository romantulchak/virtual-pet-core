package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Item;
import com.virtualpet.model.Sub;
import com.virtualpet.model.Views;
import com.virtualpet.model.items.Armor;

@JsonTypeName("armorItem")
public class ArmorDTO extends Item {


    @JsonView({Views.ShopView.class, Views.SubView.class})
    private int armor;

    @JsonView({Views.ShopView.class, Views.SubView.class})
    private int health;
    @JsonView({Views.ShopView.class})
    private boolean isBought;

    public ArmorDTO(){

    }
    public ArmorDTO(Armor armor){
        super(armor.getId(), armor.getUniqueness(), armor.getIconPath(), armor.getName(), armor.getItemCategory(), armor.getItemType(), armor.getPrice());
        this.armor = armor.getArmor();
        this.health = armor.getHealth();
    }

    public ArmorDTO(Armor armor, Sub sub) {
        super(armor.getId(), armor.getUniqueness(), armor.getIconPath(), armor.getName(), armor.getItemCategory(), armor.getItemType(), armor.getPrice());
        this.armor = armor.getArmor();
        this.health = armor.getHealth();
        this.isBought = sub.getInventory().getArmors().contains(armor);
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }
}
