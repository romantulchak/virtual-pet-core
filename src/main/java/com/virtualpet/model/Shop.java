package com.virtualpet.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.items.Armor;
import com.virtualpet.model.items.Sword;
import com.virtualpet.model.skills.DamageSkill;
import com.virtualpet.model.skills.DefenceSkill;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    private List<DamageSkill> damageSkills = new ArrayList<>();


    @OneToMany
    private List<DefenceSkill> defenceSkills = new ArrayList<>();

    @OneToMany
    private List<Sword> itemSwords = new ArrayList<>();

    @OneToMany
    private List<Armor> itemArmors = new ArrayList<>();



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<DamageSkill> getDamageSkills() {
        return damageSkills;
    }

    public void setDamageSkills(List<DamageSkill> damageSkills) {
        this.damageSkills = damageSkills;
    }

    public List<DefenceSkill> getDefenceSkills() {
        return defenceSkills;
    }

    public void setDefenceSkills(List<DefenceSkill> defenceSkills) {
        this.defenceSkills = defenceSkills;
    }

    public List<Sword> getItemSwords() {
        return itemSwords;
    }

    public void setItemSwords(List<Sword> itemSwords) {
        this.itemSwords = itemSwords;
    }

    public List<Armor> getItemArmors() {
        return itemArmors;
    }

    public void setItemArmors(List<Armor> itemArmors) {
        this.itemArmors = itemArmors;
    }


}
