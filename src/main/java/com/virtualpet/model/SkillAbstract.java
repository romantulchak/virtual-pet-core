package com.virtualpet.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.enums.ESkillCategory;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class SkillAbstract {
    @JsonView(Views.SubView.class)
    private String name;

    @Enumerated(EnumType.STRING)
    private ESkillCategory skillCategory;
    @JsonView(Views.SubView.class)
    private int price;
    @JsonView(Views.SubView.class)
    private String skillDescription;
    @JsonView(Views.SubView.class)
    private LocalDateTime cooldown;

    public SkillAbstract(){

    }
    public SkillAbstract(String name, ESkillCategory skillCategory, int price, String skillDescription, LocalDateTime cooldown) {
        this.name = name;
        this.skillCategory = skillCategory;
        this.price = price;
        this.skillDescription = skillDescription;
        this.cooldown = cooldown;
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getCooldown() {
        return cooldown;
    }

    public void setCooldown(LocalDateTime cooldown) {
        this.cooldown = cooldown;
    }
}
