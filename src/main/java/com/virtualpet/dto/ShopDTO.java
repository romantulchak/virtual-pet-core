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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShopDTO {

    private long id;

    @JsonView(Views.ShopView.class)
    private List<DamageSkill> damageSkills = new ArrayList<>();

    @JsonView(Views.ShopView.class)
    private List<DefenceSkill> defenceSkills = new ArrayList<>();

    @JsonView(Views.ShopView.class)
    private List<Sword> itemSwords = new ArrayList<>();

    @JsonView(Views.ShopView.class)
    private List<Armor> itemArmors = new ArrayList<>();

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

    public List<Sword> getItemSwords() {
        return itemSwords;
    }

    public void setItemSwords(List<Sword> itemSwords) {
        this.itemSwords = itemSwords;
    }

    public List<Armor> getItemArmors() {
        return itemArmors;
    }

    public void setItemArmors(List<Armor> itemArmors) {
        this.itemArmors = itemArmors;
    }
}
