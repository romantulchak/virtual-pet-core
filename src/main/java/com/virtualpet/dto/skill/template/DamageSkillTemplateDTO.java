package com.virtualpet.dto.skill.template;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Shop;
import com.virtualpet.model.Sub;
import com.virtualpet.model.Views;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DamageSkillTemplateDTO extends SkillAbstractTemplateDTO {

    @JsonView({Views.SkillView.class, Views.ShopView.class, Views.SubView.class})
    private int damage;

    @JsonView({Views.SkillView.class, Views.ShopView.class, Views.SubView.class})
    private double criticalChance;

    private List<Sub> subs;

    private Shop shop;
}
