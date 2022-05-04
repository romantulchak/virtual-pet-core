package com.virtualpet.dto.skill.template;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Shop;
import com.virtualpet.model.Views;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DefenceSkillTemplateDTO extends SkillAbstractTemplateDTO {

    @JsonView({Views.SkillView.class, Views.ShopView.class, Views.SubView.class})
    private int health;

    @JsonView({Views.SkillView.class, Views.ShopView.class, Views.SubView.class})
    private double defence;

    @JsonView({Views.SkillView.class, Views.ShopView.class, Views.SubView.class})
    private int timeOfAction;

    private Shop shop;
}
