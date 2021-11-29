package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Shop;
import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.Sub;
import com.virtualpet.model.Views;
import com.virtualpet.model.skills.DamageSkill;

import java.util.List;

@JsonTypeName("damageSkill")
public class DamageSkillDTO extends SkillAbstract {

    @JsonView({Views.SkillView.class, Views.ShopView.class, Views.SubView.class})
    private int damage;
    @JsonView({Views.SkillView.class, Views.ShopView.class, Views.SubView.class})
    private double criticalChance;

    private List<Sub> subs;

    private Shop shop;
    @JsonView(Views.SkillView.class)
    private boolean inShop = false;


    @JsonView(Views.ShopView.class)
    private boolean isBought;

    public DamageSkillDTO(DamageSkill damageSkill) {
        super(damageSkill.getId(),damageSkill.getName(), damageSkill.getSkillCategory(), damageSkill.getPrice(), damageSkill.getSkillDescription(), damageSkill.getCooldown(), damageSkill.getMaxCooldown(), damageSkill.getIcon());
        this.damage = damageSkill.getDamage();
        this.criticalChance = damageSkill.getCriticalChance();
        this.subs = damageSkill.getSubs();
        if(damageSkill.getShop() != null){
            this.inShop = damageSkill.getShop().getDamageSkills().contains(damageSkill);
        }
    }

    public DamageSkillDTO(DamageSkill damageSkill, Sub sub){
        super(damageSkill.getId(),damageSkill.getName(), damageSkill.getSkillCategory(), damageSkill.getPrice(), damageSkill.getSkillDescription(), damageSkill.getCooldown(), damageSkill.getMaxCooldown(), damageSkill.getIcon());
        this.damage = damageSkill.getDamage();
        this.criticalChance = damageSkill.getCriticalChance();
        this.subs = damageSkill.getSubs();
        this.isBought = sub.getDamageSkills().contains(damageSkill);
    }



    public DamageSkillDTO() {
    }


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getCriticalChance() {
        return criticalChance;
    }

    public void setCriticalChance(double criticalChance) {
        this.criticalChance = criticalChance;
    }

    public List<Sub> getSubs() {
        return subs;
    }

    public void setSubs(List<Sub> subs) {
        this.subs = subs;
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
