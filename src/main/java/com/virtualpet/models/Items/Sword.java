package com.virtualpet.models.Items;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.models.Enums.EItemCategory;
import com.virtualpet.models.Enums.EItemType;
import com.virtualpet.models.Enums.EUniqueness;
import com.virtualpet.models.Inventory;
import com.virtualpet.models.Item;
import com.virtualpet.models.Views;

import javax.persistence.*;
import java.util.List;

@Entity
public class Sword extends Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.InventoryView.class, Views.SubView.class})
    private long id;

    @JsonView({Views.InventoryView.class, Views.SubView.class} )
    private int attack;

    @JsonView({Views.InventoryView.class, Views.SubView.class})
    private boolean allowShield;

    @ManyToMany
    private List<Inventory> inventory;


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
}
