package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.*;
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
    private List<DamageSkillDTO> damageSkills = new ArrayList<>();

    @JsonView(Views.ShopView.class)
    private List<DefenceSkillDTO> defenceSkills = new ArrayList<>();

    @JsonView(Views.ShopView.class)
    private List<SwordDTO> itemSwords = new ArrayList<>();

    @JsonView(Views.ShopView.class)
    private List<ArmorDTO> itemArmors = new ArrayList<>();

    public ShopDTO(){

    }

    public ShopDTO(Shop shop, List<DamageSkillDTO> damageSkills, List<DefenceSkillDTO> defenceSkills, List<SwordDTO> swords, List<ArmorDTO> armors) {
        this.id = shop.getId();
        this.damageSkills = damageSkills;
        this.defenceSkills = defenceSkills;
        this.itemSwords = swords;
        this.itemArmors = armors;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<DamageSkillDTO> getDamageSkills() {
        return damageSkills;
    }

    public void setDamageSkills(List<DamageSkillDTO> damageSkills) {
        this.damageSkills = damageSkills;
    }

    public List<DefenceSkillDTO> getDefenceSkills() {
        return defenceSkills;
    }

    public void setDefenceSkills(List<DefenceSkillDTO> defenceSkills) {
        this.defenceSkills = defenceSkills;
    }

    public List<SwordDTO> getItemSwords() {
        return itemSwords;
    }

    public void setItemSwords(List<SwordDTO> itemSwords) {
        this.itemSwords = itemSwords;
    }

    public List<ArmorDTO> getItemArmors() {
        return itemArmors;
    }

    public void setItemArmors(List<ArmorDTO> itemArmors) {
        this.itemArmors = itemArmors;
    }
}
