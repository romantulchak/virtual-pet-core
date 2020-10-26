package com.virtualpet.models;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Embeddable;

@Embeddable
public class Currency {

    @JsonView(Views.SubView.class)
    private long money;

    @JsonView(Views.SubView.class)
    private long diamond;

    @JsonView(Views.SubView.class)
    private long emerald;

    @JsonView(Views.SubView.class)
    private int maxDiamond;

    public Currency(){
        this.money = 0;
        this.diamond = 0;
        this.emerald = 0;
        this.maxDiamond = 300;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public long getDiamond() {
        return diamond;
    }

    public void setDiamond(long diamond) {
        this.diamond = diamond;
    }

    public long getEmerald() {
        return emerald;
    }

    public void setEmerald(long emerald) {
        this.emerald = emerald;
    }

    public int getMaxDiamond() {
        return maxDiamond;
    }

    public void setMaxDiamond(int maxDiamond) {
        this.maxDiamond = maxDiamond;
    }
}
