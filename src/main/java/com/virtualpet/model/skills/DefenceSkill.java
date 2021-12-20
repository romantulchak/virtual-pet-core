package com.virtualpet.model.skills;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.Views;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

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

}
