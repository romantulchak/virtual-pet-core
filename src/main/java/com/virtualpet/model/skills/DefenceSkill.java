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
import java.util.Objects;

@JsonTypeName("defenceSkill")
@Entity
@DiscriminatorValue(value = "DefenceSkill")
@Getter
@Setter
public class DefenceSkill extends SkillAbstract {

    @JsonView(Views.ShopView.class)
    private int health;

    @JsonView(Views.ShopView.class)
    private double defence;

    @JsonView(Views.ShopView.class)
    private int timeOfAction;

    public DefenceSkill() {
    }

    public DefenceSkill(DefenceSkill defenceSkill, Sub sub) {
        super(defenceSkill, sub);
        this.health = defenceSkill.getHealth();
        this.defence = defenceSkill.getDefence();
        this.timeOfAction = defenceSkill.getTimeOfAction();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DefenceSkill that = (DefenceSkill) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
