package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.items.Armor;
import com.virtualpet.model.items.Sword;
import com.virtualpet.model.Views;

import java.util.List;

public class InventoryDTO {

    @JsonView(Views.InventoryView.class)
    private long id;

    @JsonView(Views.InventoryView.class)
    private int maxInventorySize;

    @JsonView(Views.InventoryView.class)
    private List<Sword> swords;

    @JsonView(Views.InventoryView.class)
    private List<Armor> armors;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMaxInventorySize() {
        return maxInventorySize;
    }

    public void setMaxInventorySize(int maxInventorySize) {
        this.maxInventorySize = maxInventorySize;
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
}
