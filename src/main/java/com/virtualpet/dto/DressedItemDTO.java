package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.DressedItem;
import com.virtualpet.model.Views;
import com.virtualpet.model.items.Armor;
import com.virtualpet.model.items.Sword;
import lombok.Data;

@Data
public class DressedItemDTO {

    @JsonView({Views.SubView.class,Views.InventoryView.class})
    private long id;

    @JsonView({Views.SubView.class,Views.InventoryView.class})
    private Sword sword;

    @JsonView({Views.SubView.class,Views.InventoryView.class})
    private Armor legs;

    @JsonView({Views.SubView.class,Views.InventoryView.class})
    private Armor shoulders;

    @JsonView({Views.SubView.class,Views.InventoryView.class})
    private Armor body;

    @JsonView({Views.SubView.class,Views.InventoryView.class})
    private Armor hands;

    @JsonView({Views.SubView.class,Views.InventoryView.class})
    private Armor head;

    @JsonView({Views.SubView.class, Views.InventoryView.class})
    private Armor shield;

    public DressedItemDTO(){

    }

    public DressedItemDTO(DressedItem dressedItem) {
        this.id = dressedItem.getId();
        this.sword = dressedItem.getSword();
        this.legs = dressedItem.getLegs();
        this.shoulders = dressedItem.getShoulders();
        this.body = dressedItem.getBody();
        this.hands = dressedItem.getHands();
        this.head = dressedItem.getHead();
        this.shield = dressedItem.getShield();
    }
}
