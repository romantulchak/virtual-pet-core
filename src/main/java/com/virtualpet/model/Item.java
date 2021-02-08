package com.virtualpet.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.enums.EItemCategory;
import com.virtualpet.model.enums.EItemType;
import com.virtualpet.model.enums.EUniqueness;
import com.virtualpet.model.items.Armor;
import com.virtualpet.model.items.Sword;
import com.virtualpet.model.skills.DamageSkill;
import com.virtualpet.model.skills.DefenceSkill;

import javax.persistence.*;

@MappedSuperclass
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Sword.class, name = "swordItem"),
        @JsonSubTypes.Type(value = Armor.class, name = "armorItem")
})
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.ShopView.class)
    private long id;

    @Enumerated(EnumType.STRING)
    @JsonView({Views.InventoryView.class, Views.SubView.class, Views.ShopView.class})
    private EUniqueness uniqueness;

    @JsonView({Views.InventoryView.class,Views.ShopView.class})
    private String iconPath;

    @JsonView({Views.InventoryView.class, Views.SubView.class, Views.ShopView.class})
    private String name;
    @JsonView({Views.InventoryView.class, Views.ShopView.class})
    @Enumerated(EnumType.STRING)
    private EItemType eItemType;
    @Enumerated(EnumType.STRING)
    @JsonView({Views.InventoryView.class, Views.ShopView.class})
    private EItemCategory eItemCategory;
    @JsonView({Views.InventoryView.class, Views.ShopView.class})
    private int price;

    protected Item(long id, EUniqueness uniqueness, String iconPath, String name, EItemCategory eItemCategory, EItemType eItemType, int price){
        this.id = id;
        this.uniqueness = uniqueness;
        this.iconPath = iconPath;
        this.name = name;
        this.eItemCategory = eItemCategory;
        this.eItemType = eItemType;
        this.price = price;
    }
    protected Item(){

    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EUniqueness getUniqueness() {
        return uniqueness;
    }

    public void setUniqueness(EUniqueness uniqueness) {
        this.uniqueness = uniqueness;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EItemCategory geteItemCategory() {
        return eItemCategory;
    }

    public void seteItemCategory(EItemCategory eItemCategory) {
        this.eItemCategory = eItemCategory;
    }

    public EItemType geteItemType() {
        return eItemType;
    }

    public void seteItemType(EItemType eItemType) {
        this.eItemType = eItemType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

