package com.virtualpet.model.skills;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Sub;
import com.virtualpet.model.Views;
import com.virtualpet.model.skills.template.DamageSkillTemplate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@JsonTypeName("damageSkill")
@DiscriminatorValue(value = "DamageSkill")
@Table(name = "damage_skill")
@Getter
@Setter
public class DamageSkill extends SkillAbstract{

    @JsonView(Views.ShopView.class)
    private int damage;

    @JsonView(Views.ShopView.class)
    private double criticalChance;

    public DamageSkill() {
    }

    public DamageSkill(DamageSkillTemplate damageSkill, Sub sub) {
        super(damageSkill, sub);
        this.damage = damageSkill.getDamage();
        this.criticalChance = damageSkill.getCriticalChance();
    }
}
