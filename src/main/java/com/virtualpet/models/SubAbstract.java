package com.virtualpet.models;

import javax.persistence.*;


@MappedSuperclass
public abstract class SubAbstract {

    private Integer attack;

    private Integer defence;

    private Integer health;

    private String iconPath;

    private String modelPath;


    public SubAbstract(){}


    public SubAbstract(Integer attack, Integer defence) {

        this.attack = attack;
        this.defence = defence;
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

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }
}
