package com.virtualpet.dtos;

import com.virtualpet.models.Inventory;
import com.virtualpet.models.Sub;

import javax.persistence.OneToOne;

public class SubDTO {

    private Long id;

    private String name;

    private Integer attack;

    private Integer money;

    private Integer defence;

    private Inventory inventory;

    public SubDTO(Sub sub) {
        this.id = sub.getId();
        this.name = sub.getName();
        this.attack = sub.getAttack();
        this.defence = sub.getDefence();
        this.money = sub.getMoney();
        this.inventory = sub.getInventory();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getDefence() {
        return defence;
    }

    public void setDefence(Integer defence) {
        this.defence = defence;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
