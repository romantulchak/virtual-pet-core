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
import java.util.ArrayList;
import java.util.List;

@JsonTypeName("defenceSkill")
@Entity
@Getter
@Setter
public class DefenceSkill extends SkillAbstract {

    @JsonView(Views.ShopView.class)
    private int health;

    @JsonView(Views.ShopView.class)
    private double defence;

    @JsonView(Views.ShopView.class)
    private int timeOfAction;

    @ManyToOne
    private Shop shop;

    @ManyToMany(mappedBy = "defenceSkills")
    private List<Sub> subs = new ArrayList<>();


    public DefenceSkill() {
    }

    public DefenceSkill(String name, ESkillCategory skillCategory, int price, String skillDescription, LocalDateTime cooldown, int maxCooldown, long id, int health, double defence, int timeOfAction, String skillImage) {
        super(id, name, skillCategory, price, skillDescription, cooldown, maxCooldown, skillImage);
        this.health = health;
        this.defence = defence;
        this.timeOfAction = timeOfAction;
    }

}
