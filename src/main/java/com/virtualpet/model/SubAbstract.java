package com.virtualpet.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@MappedSuperclass
@Getter
@Setter
public abstract class SubAbstract {

    private int attack;

    private int defence;

    private int health;

    @NotBlank
    private String iconPath;

    @NotBlank
    private String modelPath;

    protected SubAbstract(){}

    protected SubAbstract(Integer attack, Integer defence) {
        this.attack = attack;
        this.defence = defence;
    }

}
