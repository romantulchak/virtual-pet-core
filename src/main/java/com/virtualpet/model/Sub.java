package com.virtualpet.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.skills.DamageSkill;
import com.virtualpet.model.skills.DefenceSkill;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "sub", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
@Getter
@Setter
public class Sub extends SubAbstract {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.SubView.class)
    private long id;


    @JsonView({Views.SubView.class, Views.FriendView.class})
    @NotBlank
    @Size(max = 40)
    private String name;

    @OneToOne
    @JsonView(Views.SubView.class)
    private Inventory inventory;

    @ManyToOne
    @JsonView(Views.SubView.class)
    private User user;

    @JsonView(Views.SubView.class)
    private int moneyUpLevel;

    @JsonView(Views.SubView.class)
    private long moneyUpPrice;

    @ManyToOne
    @JsonView(Views.FriendView.class)
    private SubType subType;

    @JsonView(Views.SubView.class)
    private int moneyMultiplier;

    @ManyToOne
    private Level level;

    @Embedded
    private SubAttack subAttack;

    @Embedded
    private Currency currency;

    @OneToOne
    private DressedItem dressedItems;

    @ManyToMany
    @JoinTable(name = "sub_damage_skill", joinColumns = @JoinColumn(name = "subId"), inverseJoinColumns = @JoinColumn(name = "damageSkillId"))
    List<DamageSkill> damageSkills;

    @ManyToMany
    @JoinTable(name = "sub_defence_skill", joinColumns = @JoinColumn(name = "subId"), inverseJoinColumns = @JoinColumn(name = "defenceSkillId"))
    List<DefenceSkill> defenceSkills;

    public Sub(){

    }
    public Sub(String name, Inventory inventory, User user, SubType subType, String modelPath, String iconPath, Level level, DressedItem dressedItems) {
        this.name = name;
        setAttack(0);
        this.currency = new Currency();
        setDefence(0);
        this.inventory = inventory;
        this.user = user;
        this.moneyUpLevel = 1;
        this.subType = subType;
        this.moneyUpPrice = 50L;
        this.moneyMultiplier = 5;
        setModelPath(modelPath);
        setIconPath(iconPath);
        this.level = level;
        this.subAttack = new SubAttack();
        setHealth(0);
        this.dressedItems = dressedItems;
    }

    @PreRemove
    public void PreRemove(){
        setDefenceSkills(null);
        setDamageSkills(null);
    }
}
