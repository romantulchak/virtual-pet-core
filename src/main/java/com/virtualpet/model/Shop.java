package com.virtualpet.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.items.Armor;
import com.virtualpet.model.items.Sword;
import com.virtualpet.model.skills.DamageSkill;
import com.virtualpet.model.skills.DefenceSkill;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    @JsonView(Views.SubView.class)
    private Set<DamageSkill> damageSkills = new HashSet<>();


    @OneToMany
    @JsonView(Views.SubView.class)
    private Set<DefenceSkill> defenceSkills = new HashSet<>();

    @OneToMany
    @JsonView(Views.SubView.class)
    private Set<Sword> itemSwords = new HashSet<>();

    @OneToMany
    @JsonView(Views.SubView.class)
    private Set<Armor> itemArmors = new HashSet<>();



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<DamageSkill> getDamageSkills() {
        return damageSkills;
    }

    public void setDamageSkills(Set<DamageSkill> damageSkills) {
        this.damageSkills = damageSkills;
    }

    public Set<DefenceSkill> getDefenceSkills() {
        return defenceSkills;
    }

    public void setDefenceSkills(Set<DefenceSkill> defenceSkills) {
        this.defenceSkills = defenceSkills;
    }

    public Set<Sword> getItemSwords() {
        return itemSwords;
    }

    public void setItemSwords(Set<Sword> itemSwords) {
        this.itemSwords = itemSwords;
    }

    public Set<Armor> getItemArmors() {
        return itemArmors;
    }

    public void setItemArmors(Set<Armor> itemArmors) {
        this.itemArmors = itemArmors;
    }


}
