package com.virtualpet.model;

import javax.persistence.*;


@MappedSuperclass
public abstract class SubAbstract {

    private int attack;

    private int defence;

    private int health;

    private String iconPath;

    private String modelPath;


    public SubAbstract(){}


    public SubAbstract(Integer attack, Integer defence) {

        this.attack = attack;
        this.defence = defence;
    }


    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }
}
