package com.virtualpet.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.items.Armor;
import com.virtualpet.model.items.Sword;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.InventoryView.class ,Views.SubView.class})
    private Long id;

    @OneToOne(mappedBy = "inventory")
    @JsonView(Views.InventoryView.class)
    private Sub sub;

    @JsonView(Views.InventoryView.class)
    private int maxInventorySize;

    @ManyToMany(mappedBy = "inventory")
    @JsonView(Views.InventoryView.class)
    private List<Sword> swords;

    @ManyToMany(mappedBy = "inventory")
    @JsonView(Views.InventoryView.class)
    private List<Armor> armors;

    public Inventory(){
        this.maxInventorySize = 10;
        this.swords = new ArrayList<>();
        this.armors = new ArrayList<>();

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sub getSub() {
        return sub;
    }

    public void setSub(Sub sub) {
        this.sub = sub;
    }

    public List<Sword> getSwords() {
        return swords;
    }

    public void setSwords(List<Sword> swords) {
        this.swords = swords;
    }

    public List<Armor> getArmors() {
        return armors;
    }

    public void setArmors(List<Armor> armors) {
        this.armors = armors;
    }

    public int getMaxInventorySize() {
        return maxInventorySize;
    }

    public void setMaxInventorySize(int maxInventorySize) {
        this.maxInventorySize = maxInventorySize;
    }


}
