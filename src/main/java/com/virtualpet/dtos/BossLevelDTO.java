package com.virtualpet.dtos;

import com.virtualpet.models.Boss;

import javax.validation.constraints.NotNull;

public class BossLevelDTO {
    private Boss boss = new Boss();

    private int level;

    public BossLevelDTO(){

    }
    public BossLevelDTO(Boss boss, int level) {
        this.boss = boss;
        this.level = level;
    }
    public BossLevelDTO(Boss boss, double multiplier, int level) {
        this.boss = boss;
        this.boss.setHealth((int)(boss.getHealth() + (boss.getHealth() * multiplier)));
        this.boss.setDroppedMoney((int)(boss.getDroppedMoney() + (boss.getDroppedMoney() * multiplier)));
        this.level = level;
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
