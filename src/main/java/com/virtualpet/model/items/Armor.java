package com.virtualpet.model.items;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Shop;
import com.virtualpet.model.enums.EItemCategory;
import com.virtualpet.model.enums.EItemType;
import com.virtualpet.model.enums.EUniqueness;
import com.virtualpet.model.Item;
import com.virtualpet.model.Views;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@JsonTypeName("armorItem")
@DiscriminatorValue(value = "Armor")
@Table(name = "armor")
@Getter
@Setter
public class Armor extends Item {

    @JsonView({Views.InventoryView.class, Views.SubView.class, Views.ShopView.class})
    private int armor;

    @JsonView({Views.InventoryView.class, Views.SubView.class, Views.ShopView.class})
    private int health;

    @ManyToOne
    private Shop shop;


    public Armor(){

    }

    public Armor(int id, EUniqueness uniqueness, String iconPath, String name, EItemCategory eItemCategory, int armor, int health, EItemType eItemType, int price) {
        super(id, uniqueness, iconPath, name, eItemCategory,eItemType, price);
        this.armor = armor;
        this.health = health;
    }

}
