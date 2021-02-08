package com.virtualpet.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.skills.DamageSkill;
import com.virtualpet.model.skills.DefenceSkill;

import javax.persistence.*;
import java.util.List;

@Entity
public class Sub extends SubAbstract {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.SubView.class)
    private Long id;


    @JsonView({Views.SubView.class, Views.FriendView.class})
    private String name;


    @OneToOne
    @JsonView(Views.SubView.class)
    private Inventory inventory;

    @ManyToOne
    @JsonView(Views.SubView.class)
    private User user;
    @JsonView(Views.SubView.class)
    private Integer moneyUpLevel;
    @JsonView(Views.SubView.class)
    private Long moneyUpPrice;

    @ManyToOne
    @JsonView(Views.FriendView.class)
    private SubType subType;

    @JsonView(Views.SubView.class)
    private Integer moneyMultiplier;

    @ManyToOne
    private Level level;

    @Embedded
    private SubAttack subAttack;

    @Embedded
    private Currency currency;

    @OneToOne
    private DressedItem dressedItems;

    @ManyToMany(mappedBy = "subs")
    List<DamageSkill> damageSkills;

    @ManyToMany(mappedBy = "subs")
    List<DefenceSkill> defenceSkills;

    public Sub(){

    }
    public Sub(String name, Integer attack, Inventory inventory, Integer defence, User user, SubType subType, String modelPath, String iconPath, Level level, SubAttack subAttack, int health, Currency currency, DressedItem dressedItems) {
        this.setName(name);
        this.setAttack(0);
        this.currency = currency;
        this.setDefence(0);
        this.inventory = inventory;
        this.user = user;
        this.moneyUpLevel = 1;
        this.subType = subType;
        this.moneyUpPrice = 50L;
        this.moneyMultiplier = 5;
        setModelPath(modelPath);
        setIconPath(iconPath);
        this.level = level;
        this.subAttack = subAttack;
        setHealth(0);
        this.dressedItems = dressedItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public SubType getSubType() {
        return subType;
    }

    public void setSubType(SubType subType) {
        this.subType = subType;
    }

    public Integer getMoneyUpLevel() {
        return moneyUpLevel;
    }

    public void setMoneyUpLevel(Integer moneyUpLevel) {
        this.moneyUpLevel = moneyUpLevel;
    }

    public Long getMoneyUpPrice() {
        return moneyUpPrice;
    }

    public void setMoneyUpPrice(Long moneyUpPrice) {
        this.moneyUpPrice = moneyUpPrice;
    }

    public Integer getMoneyMultiplier() {
        return moneyMultiplier;
    }

    public void setMoneyMultiplier(Integer moneyMultiplier) {
        this.moneyMultiplier = moneyMultiplier;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public SubAttack getSubAttack() {
        return subAttack;
    }

    public void setSubAttack(SubAttack subAttack) {
        this.subAttack = subAttack;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public DressedItem getDressedItems() {
        return dressedItems;
    }

    public void setDressedItems(DressedItem dressedItems) {
        this.dressedItems = dressedItems;
    }

    public List<DamageSkill> getDamageSkills() {
        return damageSkills;
    }

    public void setDamageSkills(List<DamageSkill> damageSkills) {
        this.damageSkills = damageSkills;
    }

    public List<DefenceSkill> getDefenceSkills() {
        return defenceSkills;
    }

    public void setDefenceSkills(List<DefenceSkill> defenceSkills) {
        this.defenceSkills = defenceSkills;
    }
}
