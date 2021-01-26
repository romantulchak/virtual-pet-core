package com.virtualpet.model;

import com.virtualpet.model.Enums.ESkillCategory;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public abstract class SkillAbstract {

    private String name;

    @Enumerated(EnumType.STRING)
    private ESkillCategory skillCategory;

    private short manaCost;

    private String skillDescription;


    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ESkillCategory getSkillCategory() {
        return skillCategory;
    }

    public void setSkillCategory(ESkillCategory skillCategory) {
        this.skillCategory = skillCategory;
    }

    public short getManaCost() {
        return manaCost;
    }

    public void setManaCost(short manaCost) {
        this.manaCost = manaCost;
    }
}
