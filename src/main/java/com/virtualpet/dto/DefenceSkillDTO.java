package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Shop;
import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.Sub;
import com.virtualpet.model.Views;
import com.virtualpet.model.skills.DefenceSkill;

public class DefenceSkillDTO extends SkillAbstract {
    @JsonView({Views.SkillView.class, Views.ShopView.class})
    private long id;
    @JsonView({Views.SkillView.class, Views.ShopView.class})
    private int health;
    @JsonView({Views.SkillView.class, Views.ShopView.class})
    private double defence;
    @JsonView({Views.SkillView.class, Views.ShopView.class})
    private int timeOfAction;

    private Shop shop;
    @JsonView(Views.SkillView.class)
    private boolean inShop = false;

    private boolean isBought;

    public DefenceSkillDTO() {
    }

    public DefenceSkillDTO(DefenceSkill defenceSkill) {
        super(defenceSkill.getId(), defenceSkill.getName(), defenceSkill.getSkillCategory(), defenceSkill.getPrice(), defenceSkill.getSkillDescription(), defenceSkill.getCooldown(), defenceSkill.getMaxCooldown(), defenceSkill.getSkillImage());
        this.health = defenceSkill.getHealth();
        this.defence = defenceSkill.getDefence();
        this.timeOfAction = defenceSkill.getTimeOfAction();
        if (defenceSkill.getShop() != null){
            this.inShop = defenceSkill.getShop().getDefenceSkills().contains(defenceSkill);
        }
    }
    public DefenceSkillDTO(DefenceSkill defenceSkill, Sub sub) {
        super(defenceSkill.getId(), defenceSkill.getName(), defenceSkill.getSkillCategory(), defenceSkill.getPrice(), defenceSkill.getSkillDescription(), defenceSkill.getCooldown(), defenceSkill.getMaxCooldown(), defenceSkill.getSkillImage());
        this.health = defenceSkill.getHealth();
        this.defence = defenceSkill.getDefence();
        this.timeOfAction = defenceSkill.getTimeOfAction();
        this.isBought = sub.getDefenceSkills().contains(defenceSkill);
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

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public boolean isInShop() {
        return inShop;
    }

    public void setInShop(boolean inShop) {
        this.inShop = inShop;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }
}
