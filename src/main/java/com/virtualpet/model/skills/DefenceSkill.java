package com.virtualpet.model.skills;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.Views;
import com.virtualpet.model.enums.ESkillCategory;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DefenceSkill extends SkillAbstract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int health;

    private double defence;

    private int timeOfAction;


    public DefenceSkill() {
    }

    public DefenceSkill(String name, ESkillCategory skillCategory, int price, String skillDescription, LocalDateTime cooldown, int maxCooldown, long id, int health, double defence, int timeOfAction) {
        super(name, skillCategory, price, skillDescription, cooldown, maxCooldown);
        this.id = id;
        this.health = health;
        this.defence = defence;
        this.timeOfAction = timeOfAction;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
