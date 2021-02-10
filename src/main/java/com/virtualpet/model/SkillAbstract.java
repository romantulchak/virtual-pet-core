package com.virtualpet.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.model.skills.DamageSkill;
import com.virtualpet.model.skills.DefenceSkill;

import javax.persistence.*;
import javax.swing.text.View;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DamageSkill.class, name = "damageSkill"),
        @JsonSubTypes.Type(value = DefenceSkill.class, name = "defenceSkill")
})
public abstract class SkillAbstract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.ShopView.class, Views.SubView.class})
    private long id;
    @JsonView({Views.SkillView.class, Views.ShopView.class, Views.SubView.class})
    private String name;

    @Enumerated(EnumType.STRING)
    @JsonView({Views.SkillView.class, Views.ShopView.class, Views.SubView.class, Views.InventoryView.class})
    private ESkillCategory skillCategory;
    @JsonView({Views.SkillView.class, Views.ShopView.class, Views.SubView.class, Views.InventoryView.class})
    private int price;
    @JsonView({Views.SkillView.class, Views.SubView.class,Views.InventoryView.class})
    private String skillDescription;
    @JsonView({Views.SkillView.class, Views.SubView.class, Views.InventoryView.class})
    private LocalDateTime cooldown;
    @JsonView({Views.SkillView.class, Views.ShopView.class, Views.SubView.class, Views.InventoryView.class})
    private int maxCooldown;
    @JsonView({Views.SkillView.class, Views.ShopView.class, Views.SubView.class, Views.InventoryView.class})
    private String skillImage;

    public SkillAbstract(){

    }
    public SkillAbstract(long id, String name, ESkillCategory skillCategory, int price, String skillDescription, LocalDateTime cooldown, int maxCooldown, String skillImage) {
        this.id = id;
        this.name = name;
        this.skillCategory = skillCategory;
        this.price = price;
        this.skillDescription = skillDescription;
        this.cooldown = cooldown;
        this.maxCooldown = maxCooldown;
        this.skillImage = skillImage;
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

    public int getMaxCooldown() {
        return maxCooldown;
    }

    public void setMaxCooldown(int maxCooldown) {
        this.maxCooldown = maxCooldown;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SkillAbstract)) return false;
        SkillAbstract that = (SkillAbstract) o;
        return id == that.id && Objects.equals(name, that.name) && skillCategory == that.skillCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, skillCategory);
    }

    public String getSkillImage() {
        return skillImage;
    }

    public void setSkillImage(String skillImage) {
        this.skillImage = skillImage;
    }
}
