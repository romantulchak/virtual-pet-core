package com.virtualpet.model.skills;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.Sub;
import com.virtualpet.model.SubType;
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
    @JsonView(Views.SubView.class)
    private int damage;
    @JsonView(Views.SubView.class)
    private short criticalChance;

    @ManyToMany
    @JoinTable(name = "sub_damage_skill", joinColumns = @JoinColumn(name = "damageSkillId"), inverseJoinColumns = @JoinColumn(name = "subId"))
    private List<Sub> subs;

    public DamageSkill(){

    }

    public DamageSkill(String name, ESkillCategory skillCategory, int price, String skillDescription, LocalDateTime cooldown, long id, int damage, short criticalChance) {
        super(name, skillCategory, price, skillDescription, cooldown);
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

    public short getCriticalChance() {
        return criticalChance;
    }

    public void setCriticalChance(short criticalChance) {
        this.criticalChance = criticalChance;
    }

    public List<Sub> getSubs() {
        return subs;
    }

    public void setSubs(List<Sub> subs) {
        this.subs = subs;
    }
}

