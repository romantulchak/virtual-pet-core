package com.virtualpet.model.skills;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Sub;
import com.virtualpet.model.Views;
import com.virtualpet.model.skills.template.DefenceSkillTemplate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@JsonTypeName("defenceSkill")
@DiscriminatorValue(value = "DefenceSkill")
@Table(name = "defence_skill")
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
        super();
    }

    protected DefenceSkill(DefenceSkillTemplate skillAbstract, Sub sub) {
        super(skillAbstract, sub);
    }
}
