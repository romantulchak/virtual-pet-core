package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.*;
import com.virtualpet.model.sub.Currency;
import com.virtualpet.model.sub.Money;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class SubDTO {

    @JsonView(Views.SubView.class)
    private long id;

    @JsonView(Views.SubView.class)
    private String name;

    @JsonView(Views.SubView.class)
    private int attack;

    @JsonView(Views.SubView.class)
    private int defence;

    @JsonView(Views.SubView.class)
    private Inventory inventory;

    @JsonView(Views.SubView.class)
    private String iconPath;

    @JsonView(Views.SubView.class)
    private User user;

    @JsonView(Views.SubView.class)
    private Currency currency;

    @JsonView(Views.SubView.class)
    private Money money;

    @JsonView(Views.SubView.class)
    private SubAttack subAttack;

    @JsonView(Views.SubView.class)
    private int health;

    @JsonView(Views.SubView.class)
    private DressedItemDTO dressedItems;

    private List<DamageSkillDTO> damageSkills;

    private List<DefenceSkillDTO> defenceSkills;
}
