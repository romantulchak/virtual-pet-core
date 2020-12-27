package com.virtualpet.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Embeddable;

@Embeddable
public class SubAttack {

    @JsonView(Views.SubView.class)
    private int attackUpLevel;

    @JsonView(Views.SubView.class)
    private int attackMultiplier;

    @JsonView(Views.SubView.class)
    private long attackMoneyUp;

    public SubAttack(){
        this.attackUpLevel = 1;
        this.attackMultiplier = 5;
        this.attackMoneyUp = 150L;
    }


    public int getAttackUpLevel() {
        return attackUpLevel;
    }

    public void setAttackUpLevel(int attackUpLevel) {
        this.attackUpLevel = attackUpLevel;
    }

    public int getAttackMultiplier() {
        return attackMultiplier;
    }

    public void setAttackMultiplier(int attackMultiplier) {
        this.attackMultiplier = attackMultiplier;
    }

    public long getAttackMoneyUp() {
        return attackMoneyUp;
    }

    public void setAttackMoneyUp(long attackMoneyUp) {
        this.attackMoneyUp = attackMoneyUp;
    }
}
