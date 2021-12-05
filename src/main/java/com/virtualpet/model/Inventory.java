package com.virtualpet.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.items.Armor;
import com.virtualpet.model.items.Sword;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inventory")
@Getter
@Setter
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.InventoryView.class ,Views.SubView.class})
    private long id;

    @OneToOne(mappedBy = "inventory")
    @JsonView(Views.InventoryView.class)
    private Sub sub;

    @JsonView(Views.InventoryView.class)
    private int maxSize;

    @OneToMany(mappedBy = "inventory")
    private List<Item> items;

    public Inventory(){
        this.maxSize = 10;
        this.items = new ArrayList<>();
    }
}
