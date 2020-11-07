package com.virtualpet.models.Items;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.models.Enums.EItemCategory;
import com.virtualpet.models.Enums.EItemType;
import com.virtualpet.models.Enums.EUniqueness;
import com.virtualpet.models.Inventory;
import com.virtualpet.models.Item;
import com.virtualpet.models.Views;

import javax.persistence.*;
import javax.swing.text.View;
import java.util.List;

@Entity
public class Armor extends Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.InventoryView.class, Views.SubView.class})
    private long id;

    @JsonView({Views.InventoryView.class, Views.SubView.class})
    private int armor;

    @JsonView({Views.InventoryView.class, Views.SubView.class})
    private int health;

    @ManyToMany
    private List<Inventory> inventory;

    public Armor(){

    }

    public Armor(int id, EUniqueness uniqueness, String iconPath, String name, EItemCategory eItemCategory, int armor, int health, EItemType eItemType) {
        super(id, uniqueness, iconPath, name, eItemCategory,eItemType);
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

 }
