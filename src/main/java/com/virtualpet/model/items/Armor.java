package com.virtualpet.model.items;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Shop;
import com.virtualpet.model.enums.EItemCategory;
import com.virtualpet.model.enums.EItemType;
import com.virtualpet.model.enums.EUniqueness;
import com.virtualpet.model.Inventory;
import com.virtualpet.model.Item;
import com.virtualpet.model.Views;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@JsonTypeName("armorItem")
@Entity
public class Armor extends Item {

    @JsonView({Views.InventoryView.class, Views.SubView.class, Views.ShopView.class})
    private int armor;

    @JsonView({Views.InventoryView.class, Views.SubView.class, Views.ShopView.class})
    private int health;

    @ManyToMany(mappedBy = "armors")
    private List<Inventory> inventory;

    @ManyToOne
    private Shop shop;


    public Armor(){

    }

    public Armor(int id, EUniqueness uniqueness, String iconPath, String name, EItemCategory eItemCategory, int armor, int health, EItemType eItemType, int price) {
        super(id, uniqueness, iconPath, name, eItemCategory,eItemType, price);
        this.armor = armor;
        this.health = health;
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


    public List<Inventory> getInventory() {
        return inventory;
    }

    public void setInventory(List<Inventory> inventory) {
        this.inventory = inventory;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

}
