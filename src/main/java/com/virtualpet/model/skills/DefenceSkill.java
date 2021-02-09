package com.virtualpet.model.skills;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Shop;
import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.Sub;
import com.virtualpet.model.Views;
import com.virtualpet.model.enums.ESkillCategory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@JsonTypeName("defenceSkill")
@Entity
public class DefenceSkill extends SkillAbstract {

    @JsonView(Views.ShopView.class)
    private int health;

    @JsonView(Views.ShopView.class)
    private double defence;

    @JsonView(Views.ShopView.class)
    private int timeOfAction;

    @ManyToOne
    private Shop shop;

    @ManyToMany(mappedBy = "defenceSkills")
    private List<Sub> subs;


    public DefenceSkill() {
    }

    public DefenceSkill(String name, ESkillCategory skillCategory, int price, String skillDescription, LocalDateTime cooldown, int maxCooldown, long id, int health, double defence, int timeOfAction, String skillImage) {
        super(id, name, skillCategory, price, skillDescription, cooldown, maxCooldown, skillImage);
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

    public List<Sub> getSubs() {
        return subs;
    }

    public void setSubs(List<Sub> subs) {
        this.subs = subs;
    }
}
