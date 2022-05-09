package com.virtualpet.model.skills.template;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Views;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@JsonTypeName("defenceSkillTemplate")
@Entity
@DiscriminatorValue(value = "DefenceSkillTemplate")
@Table(name = "defence_skill_template")
@Getter
@Setter
public class DefenceSkillTemplate extends SkillAbstractTemplate {

    private int health;

    private double defence;

    private int timeOfAction;

    public DefenceSkillTemplate() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DefenceSkillTemplate that = (DefenceSkillTemplate) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
