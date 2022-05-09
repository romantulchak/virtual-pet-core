package com.virtualpet.model.skills.template;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Views;
import com.virtualpet.model.enums.ESkillCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@JsonTypeName("damageSkillTemplate")
@Entity
@DiscriminatorValue(value = "DamageSkillTemplate")
@Table(name = "damage_skill_template")
@Getter
@Setter
@Accessors(chain = true)
public class DamageSkillTemplate extends SkillAbstractTemplate {

    private int damage;

    private double criticalChance;

    public DamageSkillTemplate(){

    }

    @Override
    public DamageSkillTemplate setName(String name) {
        super.setName(name);
        return this;
    }

    @Override
    public DamageSkillTemplate setCategory(ESkillCategory category) {
        super.setCategory(category);
        return this;
    }

    @Override
    public DamageSkillTemplate setIcon(String icon) {
        super.setIcon(icon);
        return this;
    }

    @Override
    public DamageSkillTemplate setMaxCooldown(int maxCooldown) {
        super.setMaxCooldown(maxCooldown);
        return this;
    }

    @Override
    public DamageSkillTemplate setDescription(String description) {
        super.setDescription(description);
        return this;
    }

    @Override
    public DamageSkillTemplate setReference(UUID reference) {
        super.setReference(reference);
        return this;
    }

    @Override
    public DamageSkillTemplate setPrice(int price) {
        super.setPrice(price);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DamageSkillTemplate that = (DamageSkillTemplate) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

