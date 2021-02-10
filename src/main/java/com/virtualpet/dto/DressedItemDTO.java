package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.DressedItem;
import com.virtualpet.model.Views;
import com.virtualpet.model.items.Armor;
import com.virtualpet.model.items.Sword;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Sword getSword() {
        return sword;
    }

    public void setSword(Sword sword) {
        this.sword = sword;
    }

    public Armor getLegs() {
        return legs;
    }

    public void setLegs(Armor legs) {
        this.legs = legs;
    }

    public Armor getShoulders() {
        return shoulders;
    }

    public void setShoulders(Armor shoulders) {
        this.shoulders = shoulders;
    }

    public Armor getBody() {
        return body;
    }

    public void setBody(Armor body) {
        this.body = body;
    }

    public Armor getHands() {
        return hands;
    }

    public void setHands(Armor hands) {
        this.hands = hands;
    }

    public Armor getHead() {
        return head;
    }

    public void setHead(Armor head) {
        this.head = head;
    }

    public Armor getShield() {
        return shield;
    }

    public void setShield(Armor shield) {
        this.shield = shield;
    }
}
