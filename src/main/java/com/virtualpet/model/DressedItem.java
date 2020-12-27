package com.virtualpet.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Items.Armor;
import com.virtualpet.model.Items.Sword;

import javax.persistence.*;

@Entity
public class DressedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonView(Views.SubView.class)
    @OneToOne
    private Sword sword;

    @JsonView(Views.SubView.class)
    @OneToOne
    private Armor legs;
    @JsonView(Views.SubView.class)
    @OneToOne
    private Armor shoulders;
    @JsonView(Views.SubView.class)
    @OneToOne
    private Armor body;
    @JsonView(Views.SubView.class)
    @OneToOne
    private Armor hands;
    @JsonView(Views.SubView.class)
    @OneToOne
    private Armor head;
    @JsonView(Views.SubView.class)
    @OneToOne
    private Armor shield;

    public DressedItem(){
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
