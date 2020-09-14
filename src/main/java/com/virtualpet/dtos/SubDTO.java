package com.virtualpet.dtos;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.models.Inventory;
import com.virtualpet.models.Sub;
import com.virtualpet.models.User;
import com.virtualpet.models.Views;

import javax.persistence.OneToOne;

public class SubDTO {

    @JsonView(Views.SubView.class)
    private Long id;

    @JsonView(Views.SubView.class)
    private String name;

    @JsonView(Views.SubView.class)
    private Integer attack;

    @JsonView(Views.SubView.class)
    private Integer money;

    @JsonView(Views.SubView.class)
    private Integer defence;

    @JsonView(Views.SubView.class)
    private Inventory inventory;

    @JsonView(Views.SubView.class)
    private User user;

    public SubDTO(Sub sub) {
        this.id = sub.getId();
        this.name = sub.getName();
        this.attack = sub.getAttack();
        this.defence = sub.getDefence();
        this.money = sub.getMoney();
        this.inventory = sub.getInventory();
        this.user = sub.getUser();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
