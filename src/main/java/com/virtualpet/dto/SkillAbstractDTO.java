package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Views;
import com.virtualpet.model.enums.ESkillCategory;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

public class SkillAbstractDTO {
    @JsonView(Views.SubView.class)
    private String name;

    @Enumerated(EnumType.STRING)
    @JsonView(Views.SubView.class)
    private ESkillCategory skillCategory;
    @JsonView(Views.SubView.class)
    private int price;
    @JsonView(Views.SubView.class)
    private String skillDescription;
    @JsonView(Views.SubView.class)
    private LocalDateTime cooldown;
    @JsonView(Views.SubView.class)
    private int maxCooldown;

    public SkillAbstractDTO(String name, ESkillCategory skillCategory, int price, String skillDescription, LocalDateTime cooldown, int maxCooldown) {
        this.name = name;
        this.skillCategory = skillCategory;
        this.price = price;
        this.skillDescription = skillDescription;
        this.cooldown = cooldown;
        this.maxCooldown = maxCooldown;
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

    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }

    public LocalDateTime getCooldown() {
        return cooldown;
    }

    public void setCooldown(LocalDateTime cooldown) {
        this.cooldown = cooldown;
    }

    public int getMaxCooldown() {
        return maxCooldown;
    }

    public void setMaxCooldown(int maxCooldown) {
        this.maxCooldown = maxCooldown;
    }
}
