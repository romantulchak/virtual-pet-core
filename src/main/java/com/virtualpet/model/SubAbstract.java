package com.virtualpet.model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@MappedSuperclass
@Getter
public abstract class SubAbstract {

    private int attack;

    private int defence;

    private int health;

    @NotBlank
    private String iconPath;

    @NotBlank
    private String modelPath;

    protected SubAbstract(){}

    protected SubAbstract(int attack, int defence, int health, String iconPath) {
        this.attack = attack;
        this.defence = defence;
        this.health = health;
        this.iconPath = iconPath;
    }

    public SubAbstract setAttack(int attack) {
        this.attack = attack;
        return this;
    }

    public SubAbstract setDefence(int defence) {
        this.defence = defence;
        return this;
    }

    public SubAbstract setHealth(int health) {
        this.health = health;
        return this;
    }

    public SubAbstract setIconPath(String iconPath) {
        this.iconPath = iconPath;
        return this;
    }

    public SubAbstract setModelPath(String modelPath) {
        this.modelPath = modelPath;
        return this;
    }
}
