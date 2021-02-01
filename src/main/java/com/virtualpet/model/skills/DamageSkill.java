package com.virtualpet.model.skills;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Shop;
import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.Sub;
import com.virtualpet.model.Views;
import com.virtualpet.model.enums.ESkillCategory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class DamageSkill extends SkillAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int damage;

    private double criticalChance;

    @ManyToOne
    private Shop shop;

    @ManyToMany
    @JoinTable(name = "sub_damage_skill", joinColumns = @JoinColumn(name = "damageSkillId"), inverseJoinColumns = @JoinColumn(name = "subId"))
    private List<Sub> subs;

    public DamageSkill(){

    }

    public DamageSkill(String name, ESkillCategory skillCategory, int price, String skillDescription, LocalDateTime cooldown, long id, int damage, double criticalChance, int maxCooldown) {
        super(name, skillCategory, price, skillDescription, cooldown, maxCooldown);
        this.id = id;
        this.damage = damage;
        this.criticalChance = criticalChance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}

