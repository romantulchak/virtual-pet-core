package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.Sub;
import com.virtualpet.model.Views;
import com.virtualpet.model.skills.DamageSkill;

import java.util.List;

public class DamageSkillDTO extends SkillAbstract {

    @JsonView(Views.SubView.class)
    private long id;
    @JsonView(Views.SubView.class)
    private int damage;
    @JsonView(Views.SubView.class)
    private double criticalChance;

    private List<Sub> subs;


    public DamageSkillDTO(DamageSkill damageSkill) {
        super(damageSkill.getName(), damageSkill.getSkillCategory(), damageSkill.getDamage(), damageSkill.getSkillDescription(), damageSkill.getCooldown(), damageSkill.getMaxCooldown());
        this.id = damageSkill.getId();
        this.damage = damageSkill.getDamage();
        this.criticalChance = damageSkill.getCriticalChance();
        this.subs = damageSkill.getSubs();
    }

    public DamageSkillDTO() {
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
}
