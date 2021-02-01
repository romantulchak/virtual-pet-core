package com.virtualpet.model;

import com.virtualpet.model.skills.DamageSkill;
import com.virtualpet.model.skills.DefenceSkill;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    private Set<DamageSkill> damageSkills;


    @OneToMany
    private Set<DefenceSkill> defenceSkills;


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
}
