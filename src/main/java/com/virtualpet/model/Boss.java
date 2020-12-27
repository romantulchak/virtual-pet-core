package com.virtualpet.model;

import javax.persistence.*;

@Entity
public class Boss extends SubAbstract{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int droppedMoney;

    public Boss(int attack, int defence, String iconPath, String modelPath, int health){
        super(attack, defence);
        setIconPath(iconPath);
        setModelPath(modelPath);
        setHealth(health);
    }


    public Boss() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getDroppedMoney() {
        return droppedMoney;
    }

    public void setDroppedMoney(int droppedMoney) {
        this.droppedMoney = droppedMoney;
    }
}
