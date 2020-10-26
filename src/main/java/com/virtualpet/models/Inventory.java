package com.virtualpet.models;

import com.fasterxml.jackson.annotation.JsonView;

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

    @ManyToMany(mappedBy = "inventories")
    private List<Item> items;

    public Inventory(){
        this.maxInventorySize = 30;
        this.items = new ArrayList<>();
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getMaxInventorySize() {
        return maxInventorySize;
    }

    public void setMaxInventorySize(int maxInventorySize) {
        this.maxInventorySize = maxInventorySize;
    }
}
