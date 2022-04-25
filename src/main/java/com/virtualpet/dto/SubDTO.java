package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.*;
import com.virtualpet.model.sub.Currency;
import com.virtualpet.model.sub.Money;
import lombok.Data;

import java.util.List;

@Data
public class SubDTO {

    @JsonView({Views.SubView.class, Views.GameSubView.class})
    private long id;

    @JsonView({Views.SubView.class, Views.GameSubView.class})
    private String name;

    @JsonView({Views.SubView.class, Views.GameSubView.class})
    private int attack;

    @JsonView({Views.SubView.class ,Views.GameSubView.class})
    private int defence;

    @JsonView(Views.SubView.class)
    private Inventory inventory;

    @JsonView({Views.SubView.class, Views.GameSubView.class})
    private String iconPath;

    @JsonView({Views.SubView.class, Views.GameSubView.class})
    private User user;

    @JsonView({Views.SubView.class, Views.GameSubView.class})
    private Currency currency;

    @JsonView({Views.SubView.class, Views.GameSubView.class})
    private Money money;

    @JsonView({Views.SubView.class, Views.GameSubView.class})
    private SubAttack subAttack;

    @JsonView({Views.SubView.class, Views.GameSubView.class})
    private int health;

    @JsonView(Views.SubView.class)
    private DressedItemDTO dressedItems;

    private List<DamageSkillDTO> damageSkills;

    private List<DefenceSkillDTO> defenceSkills;
}
