package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.*;
import com.virtualpet.model.skills.DamageSkill;

import java.util.ArrayList;
import java.util.List;

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
    private String iconPath;

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
    @JsonView(Views.SubView.class)
    private DressedItem dressedItems;

    @JsonView(Views.SubView.class)
    private List<DamageSkill> damageSkills;


    public SubDTO(Sub sub) {
        this.id = sub.getId();
        this.name = sub.getName();
        this.attack = sub.getAttack() + sub.getSubType().getAttack();
        this.defence = sub.getDefence() + sub.getSubType().getDefence();
        this.currency = sub.getCurrency();
        this.inventory = sub.getInventory();
        this.user = sub.getUser();
        this.moneyUpLevel = sub.getMoneyUpLevel();
        this.moneyUpPrice = sub.getMoneyUpPrice();
        this.moneyMultiplier = sub.getMoneyMultiplier();
        this.subAttack = sub.getSubAttack();
        this.health = sub.getHealth() + sub.getSubType().getHealth();
        this.dressedItems = sub.getDressedItems();
        this.iconPath = sub.getIconPath();
        this.damageSkills = sub.getDamageSkills();
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


    public DressedItem getDressedItems() {
        return dressedItems;
    }

    public void setDressedItems(DressedItem dressedItems) {
        this.dressedItems = dressedItems;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public List<DamageSkill> getDamageSkills() {
        return damageSkills;
    }

    public void setDamageSkills(List<DamageSkill> damageSkills) {
        this.damageSkills = damageSkills;
    }
}
