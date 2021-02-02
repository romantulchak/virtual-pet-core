package com.virtualpet.model.skills;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.virtualpet.model.Shop;
import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.enums.ESkillCategory;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@JsonTypeName("defenceSkill")
@Entity
public class DefenceSkill extends SkillAbstract {

    private int health;

    private double defence;

    private int timeOfAction;

    @ManyToOne
    private Shop shop;

    public DefenceSkill() {
    }

    public DefenceSkill(String name, ESkillCategory skillCategory, int price, String skillDescription, LocalDateTime cooldown, int maxCooldown, long id, int health, double defence, int timeOfAction) {
        super(id, name, skillCategory, price, skillDescription, cooldown, maxCooldown);
        this.health = health;
        this.defence = defence;
        this.timeOfAction = timeOfAction;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getDefence() {
        return defence;
    }

    public void setDefence(double defence) {
        this.defence = defence;
    }

    public int getTimeOfAction() {
        return timeOfAction;
    }

    public void setTimeOfAction(int timeOfAction) {
        this.timeOfAction = timeOfAction;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
