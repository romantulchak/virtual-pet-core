package com.virtualpet.dto.skill;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Shop;
import com.virtualpet.model.Views;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@JsonTypeName("defenceSkill")
@Data
public class DefenceSkillDTO extends SkillAbstractDTO {

    @JsonView({Views.SkillView.class, Views.ShopView.class, Views.SubView.class})
    private int health;

    @JsonView({Views.SkillView.class, Views.ShopView.class, Views.SubView.class})
    private double defence;

    @JsonView({Views.SkillView.class, Views.ShopView.class, Views.SubView.class})
    private int timeOfAction;

    private Shop shop;

    @JsonView(Views.SkillView.class)
    private boolean inShop = false;

    private boolean isBought;

}
