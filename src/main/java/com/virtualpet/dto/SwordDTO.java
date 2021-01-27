package com.virtualpet.dto;

import com.virtualpet.model.items.Sword;

public class SwordDTO {

    private long id;
    private int attack;
    private String name;
    private boolean allowShield;



    public SwordDTO() {
    }

    public SwordDTO(Sword sword) {
        this.id = sword.getId();
        this.allowShield = sword.isAllowShield();
        this.attack = sword.getAttack();
        this.name = sword.getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAllowShield() {
        return allowShield;
    }

    public void setAllowShield(boolean allowShield) {
        this.allowShield = allowShield;
    }
}

