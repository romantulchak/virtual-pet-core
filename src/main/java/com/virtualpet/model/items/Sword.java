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

@JsonTypeName("swordItem")
@Entity
public class Sword extends Item {

    @JsonView({Views.InventoryView.class, Views.SubView.class, Views.ShopView.class} )
    private int attack;

    @JsonView({Views.InventoryView.class, Views.SubView.class, Views.ShopView.class})
    private boolean allowShield;

    @ManyToMany(mappedBy = "swords")
    private List<Inventory> inventory;

    @ManyToOne
    private Shop shop;

    public Sword(int id, EUniqueness uniqueness, String iconPath, String name, int attack, boolean allowShield, EItemCategory eItemCategory, EItemType eItemType, int price) {
        super(id, uniqueness, iconPath, name, eItemCategory, eItemType, price);
        this.attack = attack;
        this.allowShield = allowShield;
    }

    public Sword() {
        super();
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

    public List<Inventory> getInventory() {
        return inventory;
    }

    public void setInventory(List<Inventory> inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Sword{" +
                ", attack=" + attack +
                ", allowShield=" + allowShield +
                ", inventory=" + inventory +
                '}';
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
