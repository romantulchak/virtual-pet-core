package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Item;
import com.virtualpet.model.Shop;
import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.Views;
import com.virtualpet.model.items.Armor;
import com.virtualpet.model.items.Sword;
import com.virtualpet.model.skills.DamageSkill;
import com.virtualpet.model.skills.DefenceSkill;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

public class ShopDTO {

    private long id;

    @JsonView(Views.ShopView.class)
    private Set<DamageSkill> damageSkills = new HashSet<>();

    @JsonView(Views.ShopView.class)
    private Set<DefenceSkill> defenceSkills = new HashSet<>();

    @JsonView(Views.ShopView.class)
    private Set<Sword> itemSwords = new HashSet<>();

    @JsonView(Views.ShopView.class)
    private Set<Armor> itemArmors = new HashSet<>();

    public ShopDTO(){

    }

    public ShopDTO(Shop shop) {
        this.id = shop.getId();
        this.damageSkills = shop.getDamageSkills();
        this.defenceSkills = shop.getDefenceSkills();
        this.itemSwords = shop.getItemSwords();
        this.itemArmors = shop.getItemArmors();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<DamageSkill> getDamageSkills() {
        return damageSkills;
    }

    public void setDamageSkills(Set<DamageSkill> damageSkills) {
        this.damageSkills = damageSkills;
    }

    public Set<DefenceSkill> getDefenceSkills() {
        return defenceSkills;
    }

    public void setDefenceSkills(Set<DefenceSkill> defenceSkills) {
        this.defenceSkills = defenceSkills;
    }

    public Set<Sword> getItemSwords() {
        return itemSwords;
    }

    public void setItemSwords(Set<Sword> itemSwords) {
        this.itemSwords = itemSwords;
    }

    public Set<Armor> getItemArmors() {
        return itemArmors;
    }

    public void setItemArmors(Set<Armor> itemArmors) {
        this.itemArmors = itemArmors;
    }
}
