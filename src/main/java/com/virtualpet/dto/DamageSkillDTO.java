package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Shop;
import com.virtualpet.model.Sub;
import com.virtualpet.model.Views;
import com.virtualpet.model.skills.DamageSkill;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@JsonTypeName("damageSkill")
@Data
public class DamageSkillDTO extends SkillAbstractDTO {

    @JsonView({Views.SkillView.class, Views.ShopView.class, Views.SubView.class})
    private int damage;

    @JsonView({Views.SkillView.class, Views.ShopView.class, Views.SubView.class})
    private double criticalChance;

    private List<Sub> subs;

    private Shop shop;

    @JsonView(Views.SkillView.class)
    private boolean inShop = false;

    @JsonView(Views.ShopView.class)
    private boolean isBought;

    public DamageSkillDTO() {
    }

    public DamageSkillDTO(DamageSkill damageSkill) {
        this.damage = damageSkill.getDamage();
        this.criticalChance = damageSkill.getCriticalChance();
    }

}
