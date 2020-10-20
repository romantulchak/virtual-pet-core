package com.virtualpet.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Boss extends SubAbstract{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int health;
    private int droppedMoney;

    public Boss(int attack, int defence, String iconPath, String modelPath){
        super(attack, defence);
        setIconPath(iconPath);
        setModelPath(modelPath);
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDroppedMoney() {
        return droppedMoney;
    }

    public void setDroppedMoney(int droppedMoney) {
        this.droppedMoney = droppedMoney;
    }
}
