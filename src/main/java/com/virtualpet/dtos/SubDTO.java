package com.virtualpet.dtos;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.models.*;

import javax.persistence.OneToOne;

public class SubDTO {

    @JsonView(Views.SubView.class)
    private Long id;

    @JsonView(Views.SubView.class)
    private String name;

    @JsonView(Views.SubView.class)
    private Integer attack;

    @JsonView(Views.SubView.class)
    private Integer defence;

    @JsonView(Views.SubView.class)
    private Inventory inventory;

    @JsonView(Views.SubView.class)
    private User user;

    @JsonView(Views.SubView.class)
    private Integer moneyUpLevel;
    @JsonView(Views.SubView.class)
    private Long moneyUpPrice;

    @JsonView(Views.SubView.class)
    private Integer moneyMultiplier;

    @JsonView(Views.SubView.class)
    private SubAttack subAttack;

    @JsonView(Views.SubView.class)
    private Currency currency;

    @JsonView(Views.SubView.class)
    private Integer health;
    public SubDTO(Sub sub) {
        this.id = sub.getId();
        this.name = sub.getName();
        this.attack = sub.getAttack();
        this.defence = sub.getDefence();
        this.currency = sub.getCurrency();
        this.inventory = sub.getInventory();
        this.user = sub.getUser();
        this.moneyUpLevel = sub.getMoneyUpLevel();
        this.moneyUpPrice = sub.getMoneyUpPrice();
        this.moneyMultiplier = sub.getMoneyMultiplier();
        this.subAttack = sub.getSubAttack();
        this.health = sub.getHealth();
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

    public Long getMoneyUpPrice() {
        return moneyUpPrice;
    }

    public void setMoneyUpPrice(Long moneyUpPrice) {
        this.moneyUpPrice = moneyUpPrice;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Integer getMoneyUpLevel() {
        return moneyUpLevel;
    }

    public void setMoneyUpLevel(Integer moneyUpLevel) {
        this.moneyUpLevel = moneyUpLevel;
    }

    public Integer getMoneyMultiplier() {
        return moneyMultiplier;
    }

    public void setMoneyMultiplier(Integer moneyMultiplier) {
        this.moneyMultiplier = moneyMultiplier;
    }

    public SubAttack getSubAttack() {
        return subAttack;
    }

    public void setSubAttack(SubAttack subAttack) {
        this.subAttack = subAttack;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }
}
