package com.virtualpet.models;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;

@Entity
public class Sub extends SubAbstract {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.SubView.class)
    private Long id;


    @JsonView(Views.SubView.class)
    private String name;

    @JsonView(Views.SubView.class)
    private Long money;
    @OneToOne
    @JsonView(Views.SubView.class)
    private Inventory inventory;
    @ManyToOne
    @JsonView(Views.SubView.class)
    private User user;
    @JsonView(Views.SubView.class)
    private Integer moneyUpLevel;
    @JsonView(Views.SubView.class)
    private Long moneyUpPrice;

    @ManyToOne
    private SubType subType;

    @JsonView(Views.SubView.class)
    private Integer moneyMultiplier;

    @ManyToOne
    private Level level;

    @Embedded
    private SubAttack subAttack;

    public Sub(){

    }
    public Sub(String name, Integer attack, Inventory inventory, Integer defence, User user, SubType subType, String modelPath, String iconPath, Level level, SubAttack subAttack) {
        this.setName(name);
        this.setAttack(attack);
        this.money = 0L;
        this.setDefence(defence);
        this.inventory = inventory;
        this.user = user;
        this.moneyUpLevel = 1;
        this.subType = subType;
        this.moneyUpPrice = 50L;
        this.moneyMultiplier = 5;
        setModelPath(modelPath);
        setIconPath(iconPath);
        this.level = level;
        this.subAttack = subAttack;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public SubType getSubType() {
        return subType;
    }

    public void setSubType(SubType subType) {
        this.subType = subType;
    }

    public Integer getMoneyUpLevel() {
        return moneyUpLevel;
    }

    public void setMoneyUpLevel(Integer moneyUpLevel) {
        this.moneyUpLevel = moneyUpLevel;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Long getMoneyUpPrice() {
        return moneyUpPrice;
    }

    public void setMoneyUpPrice(Long moneyUpPrice) {
        this.moneyUpPrice = moneyUpPrice;
    }

    public Integer getMoneyMultiplier() {
        return moneyMultiplier;
    }

    public void setMoneyMultiplier(Integer moneyMultiplier) {
        this.moneyMultiplier = moneyMultiplier;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public SubAttack getSubAttack() {
        return subAttack;
    }

    public void setSubAttack(SubAttack subAttack) {
        this.subAttack = subAttack;
    }
}
