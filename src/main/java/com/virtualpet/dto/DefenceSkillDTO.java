package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.Views;
import com.virtualpet.model.enums.ESkillCategory;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class DefenceSkillDTO extends SkillAbstract {
    @JsonView(Views.SubView.class)
    private long id;
    @JsonView(Views.SubView.class)
    private int health;
    @JsonView(Views.SubView.class)
    private double defence;
    @JsonView(Views.SubView.class)
    private int timeOfAction;

    public DefenceSkillDTO() {
    }

    public DefenceSkillDTO(String name, ESkillCategory skillCategory, int price, String skillDescription, LocalDateTime cooldown, int maxCooldown, long id, int health, double defence, int timeOfAction) {
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
