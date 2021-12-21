package com.virtualpet.model.skills;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Sub;
import com.virtualpet.model.Views;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@JsonTypeName("damageSkill")
@Entity
@DiscriminatorValue(value = "DamageSkill")
@Table(name = "damage_skill")
@Getter
@Setter
public class DamageSkill extends SkillAbstract {

    @JsonView(Views.ShopView.class)
    private int damage;

    @JsonView(Views.ShopView.class)
    private double criticalChance;

    public DamageSkill(){

    }

    public DamageSkill(DamageSkill damageSkill, Sub sub){
        super(damageSkill, sub);
        this.damage = damageSkill.getDamage();
        this.criticalChance = damageSkill.getCriticalChance();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DamageSkill that = (DamageSkill) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

