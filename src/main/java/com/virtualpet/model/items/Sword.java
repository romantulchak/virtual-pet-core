package com.virtualpet.model.items;

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

@Entity
public class Sword extends Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.InventoryView.class, Views.SubView.class, Views.ShopView.class})
    private long id;

    @JsonView({Views.InventoryView.class, Views.SubView.class, Views.ShopView.class} )
    private int attack;

    @JsonView({Views.InventoryView.class, Views.SubView.class, Views.ShopView.class})
    private boolean allowShield;

    @ManyToMany
    private List<Inventory> inventory;

    @ManyToOne
    private Shop shop;

    public Sword(int id, EUniqueness uniqueness, String iconPath, String name, int attack, boolean allowShield, EItemCategory eItemCategory, EItemType eItemType) {
        super(id, uniqueness, iconPath, name, eItemCategory, eItemType);
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

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
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
                "id=" + id +
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
