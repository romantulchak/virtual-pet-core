package com.virtualpet.model.skills;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Shop;
import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.Sub;
import com.virtualpet.model.Views;
import com.virtualpet.model.enums.ESkillCategory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

}

