package com.virtualpet.models;

import javax.persistence.*;

@Entity
public class Sub extends SubAbstract {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer money;
    @OneToOne
    private Inventory inventory;
    @ManyToOne
    private User user;

    @ManyToOne
    private SubType subType;

    public Sub(){

    }
    public Sub(String name, Integer attack, Inventory inventory, Integer defence, User user, SubType subType, String modelPath, String iconPath) {
        this.setName(name);
        this.setAttack(attack);
        this.money = 0;
        this.setDefence(defence);
        this.inventory = inventory;
        this.user = user;
        this.subType = subType;
        setModelPath(modelPath);
        setIconPath(iconPath);
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

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public SubType getSubType() {
        return subType;
    }

    public void setSubType(SubType subType) {
        this.subType = subType;
    }
}
